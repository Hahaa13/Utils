package utilsplugin.discord;

import java.util.Optional;
import utilsplugin.utils.config;
import mindustry.gen.Call;
import discord4j.core.*;
import discord4j.gateway.intent.*;
import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Member;
import discord4j.core.event.domain.message.*;
import discord4j.core.object.entity.channel.GuildMessageChannel;

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
        String user = member.getNickname().get();
        Call.sendMessage(user + content);
      }
    });
  }
}
