package utilsplugin.events;

import utilsplugin.discord.bot;
import utilsplugin.utils.config;
import discord4j.rest.util.Color;
import arc.*;
import mindustry.maps.Map;
import mindustry.gen.*;
import mindustry.Vars;
import mindustry.game.Gamemode;
import mindustry.game.EventType.*;

public class event {
  public static void load() {
    Events.on(GameOverEvent.class, e -> {
      String win = e.winner.name;
      Map nextmap = Vars.maps.getNextMap(Gamemode.valueOf(config.get("gamemode")),null);
      bot.sendEmbed("GameEnd", "NextMap: " + nextmap.plainName() + "\nWin Team: "+ win, Color.GRAY);
    });
    Events.on(PlayerChatEvent.class, e -> {
      Player p = e.player;
      String content = e.message;
      String message = "```" + p.name + ": " + content + "```";
      bot.send(message);
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
