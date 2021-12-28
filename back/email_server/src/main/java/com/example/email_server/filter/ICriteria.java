package com.example.email_server.filter;

import org.json.simple.JSONArray;

public interface ICriteria {
    public JSONArray meetCriteria(JSONArray mails, String criteriaValue);
}
