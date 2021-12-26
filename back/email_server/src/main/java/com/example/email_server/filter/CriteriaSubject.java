package com.example.email_server.filter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CriteriaSubject implements ICriteria{
    @Override
    public JSONArray meetCriteria(JSONArray mails, String criteriaValue) {
        JSONArray subjectCriteria = new JSONArray();
        for(Object obj : mails){
            JSONObject mail = (JSONObject) obj;
            String subject = mail.get("subject").toString().toLowerCase();
            if(subject.contains(criteriaValue)){
                subjectCriteria.add(mail);
            }
        }
        return subjectCriteria;
    }
}
