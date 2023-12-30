package utilsplugin.discord;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.gateway.intent.Intent;
import discord4j.gateway.intent.IntentSet;
import discord4j.rest.util.Color;
import mindustry.Vars;
import mindustry.gen.Call;
import mindustry.maps.Map;
import utilsplugin.utils.config;

public class bot {
  public static GuildMessageChannel channel;
  public static void load() {
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
        if (content.startsWith("!maps")){
          Map map = Vars.state.map;
          sendEmbed(map.plainName(),"Size: " + map.width +", " + map.height + "\nAuthor: " + map.plainAuthor() + "\nDescription: " + map.description(), Color.DISCORD_BLACK);
        }else{
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
  public static void sendEmbed(String title, String content, Color color) {
    EmbedCreateSpec embed = EmbedCreateSpec.builder()
      .color(color)
      .title(title)
      .description(content)
      .build();
    channel.createMessage(embed).subscribe();
  }
}
