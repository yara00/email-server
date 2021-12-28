package com.example.email_server.sorting;

import com.example.email_server.facade.Message;
import org.json.simple.JSONObject;

import java.util.Collections;
import java.util.Comparator;

public interface ISort extends Comparator<JSONObject> {
    int compare(JSONObject a, JSONObject b);
    //Collections.sort(msgArr2, new SortDate());
}
