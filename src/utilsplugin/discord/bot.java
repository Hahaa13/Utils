package utilsplugin.discord;

import java.net.MalformedURLException;
import java.util.Optional;

import javax.swing.text.GapContent;

import arc.util.Log;
import utilsplugin.utils.config;
import mindustry.gen.Call;
import mindustry.core.GameState;
import mindustry.maps.Map;
import discord4j.core.*;
import discord4j.gateway.intent.*;
import discord4j.common.util.Snowflake;
import discord4j.rest.util.Color;
import discord4j.core.object.entity.Member;
import discord4j.core.event.domain.message.*;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import discord4j.core.spec.EmbedCreateSpec;

public class bot {
  public static GuildMessageChannel channel;
  public static void load() {
    GameState gameState = new GameState();
    GatewayDiscordClient gateway = DiscordClient.create(config.get("discordbot_token"))
                                                        .gateway()
                                                        .setEnabledIntents(IntentSet.of(Intent.GUILD_MEMBERS, Intent.GUILD_MESSAGES))
                                                        .login()
                                                        .block();
    channel = gateway.getChannelById(Snowflake.of(config.get("discordbot_channelid"))).ofType(GuildMessageChannel.class).block();
    gateway.on(MessageCreateEvent.class).subscribe(e -> {
      var message = e.getMessage();
      if (message.getContent().isEmpty()) return;
      var member = e.getMember().orElse(null);
      if (member == null || member.isBot()) return;
      if(message.getChannelId().equals(channel.getId())) {
        String content = message.getContent();
        if(content.startsWith("!map")) {
          int tps = gameState.serverTps;
          String mapname = gameState.map.name();
          String URL_MAP_PREVIEW = null;
          try {
            URL_MAP_PREVIEW = gameState.map.previewFile().file().toURI().toURL().toString();
          } catch (MalformedURLException error) {
            error.printStackTrace();
          }
          Log.info(URL_MAP_PREVIEW);
          String m = "Tps: " + tps + "\nMap: " + mapname;
          bot.sendEmbedImage(m, Color.DEEP_SEA, URL_MAP_PREVIEW);
        } else {
          String user = member.getDisplayName();
          Call.sendMessage("[blue][][white] " + user + ": " + content);
        }
      }
    });
  }
  public static void send(String content) {
    channel.createMessage(content).subscribe();
  }
  public static void sendEmbed(String content, Color color) {
    EmbedCreateSpec embed = EmbedCreateSpec.builder()
      .color(color)
      .title(content)
      .build();
    channel.createMessage(embed).subscribe();
  }
   public static void sendEmbedImage(String content, Color color, String URL_MAP_PREVIEW) {
    EmbedCreateSpec embed = EmbedCreateSpec.builder()
      .color(color)
      .title(content)
      .image(URL_MAP_PREVIEW)
      .build();
    channel.createMessage(embed).subscribe();
   }
}
