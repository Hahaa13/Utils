package utilsplugin;

import utilsplugin.events.*;
import utilsplugin.commands.*;
import arc.util.*;
import mindustry.*;
import mindustry.mod.*;

public class UtilsPlugin extends Plugin {
  @Override
  public void init() {
    event.load();
  }
  @Override
  public void registerClientCommands(CommandHandler handler){
    playercommands.load(hanlder);
  }
}
