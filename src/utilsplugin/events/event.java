package utilsplugin.events;

import utilsplugin.discord.bot;
import discord4j.rest.util.Color;
import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.core.*;
import mindustry.gen.*;
import mindustry.game.*;
import mindustry.game.EventType.*;

public class event {
  public static void load() {
    Events.on(PlayerChatEvent.class, e -> {
      Player p = e.player;
      String content = e.message;
      String message = "```" + p.name + ": " + content + "```";
      bot.send(message);
    });
    
    Events.on(PlayerJoin.class, e -> {
      Player p = e.player;
      if (p.admin) {
        p.name = "[red][][white]" + p.name;
      }
      String message = p.name + " đã vào máy chủ";
      bot.sendEmbed(message, Color.GREEN);
    });
    
    Events.on(PlayerLeave.class, e -> {
      Player p = e.player;
      String message = p.name + " đã rời máy chủ";
      bot.sendEmbed(message, Color.RED);
    });
  }
}
