package utilsplugin.utils;

import arc.util.Log;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;

public class config {
  public static void add(String key, String value) {
    JSONObject json = new JSONObject();
    File dir = new File("\\config\\mods\\UtilsPlugin");
    if (!dir.exists()) {
      dir.mkdirs();
      Log.info("Create Folder UtilsPlugin");
    }
    try {
      FileReader file = new FileReader("/config/mod/UtilsPlugin/config.json");
      json = new JSONObject(file);
    } catch (IOException e) {
    }
    json.put(key, value);
    try {
      FileWriter file = new FileWriter("/config/mod/UtilsPlugin/config.json");
      file.write(json.toString());
      file.close();
    } catch (IOException e) {
      Log.err("UtilsPlugin cannot load config file");
    }
  }
  public static void get(String key) {
  }
}
