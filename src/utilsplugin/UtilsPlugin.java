package utilsplugin;

import utilsplugin.events.*;
import utilsplugin.commands.*;
import utilsplugin.utils.config;
import arc.util.*;
import mindustry.*;
import mindustry.mod.*;

public class UtilsPlugin extends Plugin {
  @Override
  public void init() {
    event.load();
    config.add("Token", "token");
    config.add("SurvivalMode", "true");
  }
  @Override
  public void registerClientCommands(CommandHandler handler){
    playercommands.load(handler);
  }
}
