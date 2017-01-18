package ecs189.querying.jira;

import ecs189.querying.Util;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class JIRAQuerier {

    public static void main(String[] args) throws IOException {
        JIRAQuerier querier = new JIRAQuerier();
        String name = querier.query("DERBY-1366");
        System.out.println(name);
    }

    public JIRAQuerier() {


    }

    public String query(String jiraID) throws IOException {
        String baseURL = "https://issues.apache.org/jira/rest/api/2/issue/";
        // Query the appropriate API
        URL url = new URL(baseURL + jiraID);
        System.out.println(url);
        JSONObject json = Util.queryURL(url);

        JSONObject root = json.getJSONObject("root");
        JSONObject fields = root.getJSONObject("fields");
        JSONObject issueType = fields.getJSONObject("issuetype");
        return issueType.getString("name");
    }

}
