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
    File dir = new File("config/mods/UtilsPlugin");
    File file = new File(dir, "config.json");
    if (!dir.exists()) {
      dir.mkdirs();
      Log.info("Create Folder UtilsPlugin");
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
