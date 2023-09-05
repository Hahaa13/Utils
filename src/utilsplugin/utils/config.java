package utilsplugin.utils;

import arc.util.Log;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;

public class config {
  private static String fileconfig = "config/mods/UtilsPlugin/config.json";
  public static void add(String key, String value) {
    JSONObject json = new JSONObject();
    File dir = new File("/config/mods/UtilsPlugin");
    if (!dir.exists()) dir.mkdirs();
    try {
      FileReader file = FileReader(fileconfig);
      json = new JSONObject(file);
    } catch (IOException e) {
    }
    json.put(key, value);
    try {
      FileWriter file = new FileWriter(fileconfig);
      file.write(json.toString());
      file.close();
    } catch (IOException e) {
      Log.error("UtilsPlugin cannot write file config");
    }
  }
  public static void get(String key) {
  }
}
