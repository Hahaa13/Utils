package utilsplugin.events;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.core.*;
import mindustry.gen.*;
import mindustry.game.*;
import mindustry.game.EventType.*;

public class event {
  public static void load() {
    Events.on(ServerLoadEvent.class, e -> {
      Log.info("UtilsPlugin Loading");
    });
    Events.on(PlayerJoin.class, e-> {
      Player p = e.player;
      if (p.admin) {
        p.name = "[red][A]" + p.name;
      }
    });
  }
}
