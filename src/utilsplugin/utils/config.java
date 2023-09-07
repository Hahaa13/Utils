package utilsplugin.utils;

import arc.util.Log;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class config {
  public static void load() {
    JSONObject json = new JSONObject();
    File file = new File("config/mods/UtilsPlugin/config.json");
    if (!file.exists()) {
      file.getParentFile().mkdirs();
      try {
        file.createNewFile();
        Log.info("Create File UtilsPlugin/config.json");
        json.put("discordbot_token", "token");
        json.put("discordbot_channelid", "id");
        try {
          FileWriter fw = new FileWriter(file);
          fw.write(json.toString());
          fw.close();
        } catch (IOException e) {
          Log.err("UtilsPlugin cannot load config file");
        }
      } catch (IOException e) {}
    }
  }
  public static String get(String key) {
    try {
      FileReader file = new FileReader("config/mods/UtilsPlugin/config.json");
      JSONTokener jt = new JSONTokener(file);
      JSONObject json = new JSONObject(jt);
      file.close();
      String value = json.getString(key);
      return value;
    } catch (IOException e) {
      Log.info("UtilsPlugin cannot get config " + key);
      return null;
    }
  }
}
