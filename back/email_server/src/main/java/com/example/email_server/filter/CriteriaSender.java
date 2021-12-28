package com.example.email_server.filter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CriteriaSender implements ICriteria {

    @Override
    public JSONArray meetCriteria(JSONArray jsonArray, String criteriaValue) {
        JSONArray senderCriteria = new JSONArray();
        for(Object obj : jsonArray){
            JSONObject mail = (JSONObject) obj;
            JSONObject mailHeader = (JSONObject) mail.get("messageHeader");
            if(((String)mailHeader.get("sender")).contains(criteriaValue)){
                senderCriteria.add(mail);
            }
        }
        return senderCriteria;
    }
}
