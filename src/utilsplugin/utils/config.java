package utilsplugin.utils;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;

public class config() {
  public static String add(String key, String value) {
    JSONObject json = new JSONObject();
    json.put(key, value);
  }
  public static String get() {
  }
}
