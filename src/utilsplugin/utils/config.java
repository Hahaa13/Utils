package utilsplugin.utils;

import arc.util.Log;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;

public class config {
  public static void add(String key, String value) {
    JSONObject json = new JSONObject();
    json.put(key, value);
    try (FileWriter file = new FileWriter("config.json")) {
      file.write(json.toJSONString());
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void get(String key) {
  }
}
