package utilsplugin.discord;

import utilsplugin.utils.config;
import discord4j.core.*;
import discord4j.gateway.intent.*;
import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Member;

public class bot {
  public static void load() {
    GatewayDiscordClient gateway = DiscordClient.create(config.get("discordbot_token"))
                                                        .gateway()
                                                        .setEnabledIntents(IntentSet.of(Intent.GUILD_MEMBERS, Intent.GUILD_MESSAGES))
                                                        .login()
                                                        .block();
    gateway.on(MessageCreateEvent.class).subscribe(e -> {
      var message = e.getMessage();
      if (message.getContent().isEmpty()) return;
      var member = e.getMember().orElse(null);
      if (member == null || member.isBot()) return;

      if(message.getChannelId() == Snowflake.of(config.get("discordbot_channelid"))) {
        String content = message.getContent();
        String user = member.getNickname();
      }
    }
  }
}
