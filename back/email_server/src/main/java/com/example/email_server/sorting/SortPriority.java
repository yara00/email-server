package com.example.email_server.sorting;

import com.example.email_server.facade.Message;
import org.json.simple.JSONObject;


public class SortPriority implements ISort {
    public int compare(JSONObject a, JSONObject b)
    {
        System.out.println("A IS"+a+" B IS "+b);
        JSONObject aa = (JSONObject) a.get("messageHeader");
        JSONObject bb = (JSONObject) b.get("messageHeader");
        return bb.get("priority").toString().compareTo(aa.get("priority").toString());
    }
}
