package com.example.email_server.sorting;
import com.example.email_server.facade.Message;
import org.json.simple.JSONObject;

public class SortName implements ISort{
    public int compare(JSONObject a, JSONObject b)
    {
        System.out.println("A IS"+a+" B IS "+b);

        return a.get("name").toString().compareTo(b.get("name").toString());
    }
}
