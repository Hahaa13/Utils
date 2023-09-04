package utilsplugin.utils;

import arc.util.Log;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;

public class config() {
  public static String add(String key, String value) {
    JSONObject json = new JSONObject();
    json.put(key, value);
    try (FileWriter file = new FileWriter("config.json")) {
      file.write(json.toString(4));
      file.close();
    } catch (IOException e) {
      Log.error(e);
    }
  }
  public static String get(String key) {
  }
}
