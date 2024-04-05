package utilsplugin.events;

import utilsplugin.discord.bot;
import discord4j.rest.util.Color;
import arc.*;
import mindustry.maps.Map;
import mindustry.gen.*;
import mindustry.Vars;
import mindustry.game.EventType.*;

public class event {
  public static void load() {
    Events.on(PlayEvent.class, e -> {
      Map map = Vars.state.map;
      if (map != null) bot.sendEmbed("NewGame", "Map: " + map.plainName() + "\nAuthor: " + map.plainAuthor() + "\nSize: " + map.width + ", " + map.height, Color.GRAY);
    });
    Events.on(PlayerChatEvent.class, e -> {
      String content = e.message;
      if (!content.startsWith("/")) {
        Player p = e.player;
        String message = "```" + p.name + ": " + content + "```";
        bot.send(message);
      }
    });
    
    Events.on(PlayerJoin.class, e -> {
      Player p = e.player;
      Call.openURI(p.con, "https://discord.com/invite/e27WJNeaR3");
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
