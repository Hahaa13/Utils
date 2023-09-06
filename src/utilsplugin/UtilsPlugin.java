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
    Log.info("Loading UtilsPlugin");
    //load content
    config.load();
    event.load();
    Log.info("Loaded UtilsPlugin");
    Log.info(config.get("discordbot_token"));
  }
  @Override
  public void registerClientCommands(CommandHandler handler){
    playercommands.load(handler);
  }
}
