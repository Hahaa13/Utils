package utilsplugin.utils;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;

public class config(String key, String value) {
  public static String add() {
    JSONObject json = new JSONObject();
    json.put(key, value);
  }
  public static String get() {
  }
}
