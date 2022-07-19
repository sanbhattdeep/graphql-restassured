package model;


import lombok.Data;
import org.json.JSONObject;

@Data
public class GraphQLQuery {

    public static String graphqlToJson(String queryPayload) {

        JSONObject json = new JSONObject();
        json.put("query",queryPayload);
        return json.toString();
    }
    private String query;
    private Object variables;
}
