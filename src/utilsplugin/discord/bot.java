package utilsplugin.discord;

import utilsplugin.utils.config;
import discord4j.core.*;
import discord4j.gateway.intent.*;

public class bot {
  public static void load() {
    GatewayDiscordClient gateway = DiscordClient.create(config.get("discordbot_token"))
                                                        .build()
                                                        .gateway()
                                                        .setEnabledIntents(IntentSet.of(Intent.GUILD_MEMBERS, Intent.GUILD_MESSAGES))
                                                        .login()
                                                        .block();
    
  }
}
