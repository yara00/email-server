package com.example.email_server.sorting;

import com.example.email_server.facade.Message;
import org.json.simple.JSONObject;


public class SortSender implements ISort {
    @Override
    public int compare(JSONObject a, JSONObject b)
    {
        System.out.println("A IS"+a+" B IS "+b);
        JSONObject aa = (JSONObject) a.get("messageHeader");
        JSONObject bb = (JSONObject) b.get("messageHeader");
        return aa.get("sender").toString().compareTo(bb.get("sender").toString());
    }
}
