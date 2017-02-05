package ecs189.querying.github;

/**
 * Created by John on 1/10/17.
 */
import com.sun.codemodel.internal.JArray;
import ecs189.querying.Util;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URL;

public class GithubQuerier {

    private static final String token =  "";
    private JSONArray pullArray;
    private String account, name;

    public static void main(String[] args) throws IOException {
        GithubQuerier querier = new GithubQuerier("ReactiveX","RxJava");
        String response1 = querier.query("abersnaze" );
        //String response2 = querier.query("abersnaze");

    }

    public GithubQuerier(String account, String name) throws IOException {
        this.account = account;
        this.name = name;
    }

    public String query(String username) throws IOException {
        int numPulled = 0, numMerged = 0;
        String result;
        this.pullArray = getPullArray(account, name,username);
        for(int i = 0; i < pullArray.length();i++){
            JSONObject object = pullArray.getJSONObject(i);
            JSONObject userObject = object.getJSONObject("user");
            String user = userObject.getString("login");
            boolean isMerged = !object.isNull("merged_at");
            if(new String(user).equals(username)) {
                numPulled++;
                if(isMerged){
                    numMerged++;
                }
            }
        }
        result = String.valueOf(numPulled) + " " + String.valueOf(numMerged);
        System.out.println(result);
        return result;
    }
    private JSONArray getPullArray(String account, String name, String username) throws IOException {
        String baseURL = "https://api.github.com/repos/";
  
        String pull = "/pulls?state=closed&per_page=10000&merge=all&access_token=";
        String pullURL = baseURL + account + "/" + name + pull + token;
        URL pageURL = new URL(pullURL);
        JSONObject json = Util.queryURL(pageURL);
        System.out.println(pageURL);
        return json.getJSONArray("root");
    }
}
