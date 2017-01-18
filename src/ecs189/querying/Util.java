package ecs189.querying;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by John on 1/10/17.
 */
public class Util {
    public static JSONObject queryURL(URL url) throws IOException {
        // Read all bytes from the URL, if it exists. If it does not, the code below will throw an IOException
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = in.read()) != -1) {
            sb.append((char) cp);
        }
        in.close();
        String content = sb.toString();
        // Extract issue-type from the JSON object returned.
        String jsonText = "{root:" + content + "}";
        return new JSONObject(jsonText);
    }
}
