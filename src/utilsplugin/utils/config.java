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
    File file = new File("config/mods/UtilsPlugin/config.json");
    if (!file.exists()) {
      file.getParentFile().mkdirs();
      try {
        file.createNewFile();
      } catch (IOException e) {
      }
      Log.info("Create File UtilsPlugin/config.json");
    }
    try {
      FileReader fr = new FileReader(file);
      json = new JSONObject(fr);
    } catch (IOException e) {
    }
    json.put(key, value);
    try {
      FileWriter fw = new FileWriter(file);
      fw.write(json.toString());
      fw.close();
    } catch (IOException e) {
      Log.err("UtilsPlugin cannot load config file");
    }
  }
  public static void get(String key) {
  }
}
