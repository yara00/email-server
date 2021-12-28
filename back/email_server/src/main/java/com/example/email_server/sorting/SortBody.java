package com.example.email_server.sorting;
import com.example.email_server.facade.Message;
import org.json.simple.JSONObject;

public class SortBody implements ISort{
    public int compare(JSONObject a, JSONObject b)
    {
        System.out.println("A IS"+a+" B IS "+b);
        JSONObject aa = (JSONObject) a.get("messageBody");
        JSONObject bb = (JSONObject) b.get("messageBody");
        return aa.get("body").toString().compareTo(bb.get("body").toString());
    }
}
