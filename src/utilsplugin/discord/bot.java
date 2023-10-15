package utilsplugin.discord;

import java.util.Optional;
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
  public void load() {
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
          var map = GameState.map;
        } else {
          String user = member.getDisplayName();
          Call.sendMessage("[blue][][white] " + user + ": " + content);
        }
      }
    });
  }
  public void send(String content) {
    channel.createMessage(content).subscribe();
  }
  public void sendEmbed(String content, Color color) {
    EmbedCreateSpec embed = EmbedCreateSpec.builder()
      .color(color)
      .title(content)
      .build();
    channel.createMessage(embed).subscribe();
  }
}
