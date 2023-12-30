package utilsplugin.commands;

import arc.util.*;
import mindustry.gen.*;

public class playercommands {
  public static void load(CommandHandler handler) {
    handler.<Player>register("discord", "Discord server link", (args, player) -> Call.openURI(player.con, "https://discord.com/invite/e27WJNeaR3"));
  }
}
