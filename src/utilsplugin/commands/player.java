package utilsplugin.commands;

import arc.*;
import mindustry.*;
import mindustry.core.*;
import mindustry.game.*;
import mindustry.gen.*;

public class player {
  public static void load(CommandHandler handler) {
    handler.<Player>register("discord", "Discord server link", (args, player) -> Call.openURI(player.con, "https://discord.gg/Dxk9PxuDq4"));
  }
}
