package com.example.email_server.filter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;

public class CriteriaAll implements ICriteria {
 /*   public CriteriaAll(Date date, int priority) {
        this.date = date;
        this.priority = priority;
    }*/

    //search
    private Date date;
    private int priority;

    public JSONArray meetCriteria(JSONArray jsonArray, String criteriaValue) {
        JSONArray allCriteria = new JSONArray();
        for(Object obj : jsonArray){
            JSONObject mail = (JSONObject)obj;
            if(found(mail, criteriaValue)){
                allCriteria.add(mail);
            }
        }
        System.out.print("ffffffffffff");
        return allCriteria;
    }
    private boolean searchSubject(JSONObject mailHeader, String criteriaValue){
        String subject = (String) mailHeader.get("subject");
        if(subject.contains(criteriaValue)) return true;
        return false;
    }
    private boolean searchSender(JSONObject mailHeader, String criteriaValue){
        String sender = mailHeader.get("sender").toString();
        if(sender.contains(criteriaValue)) return true;
        return false;
    }
    private boolean searchReceivers(JSONObject mailHeader,String criteriaValue){
        JSONArray receivers = (JSONArray) mailHeader.get("receivers");
        for(Object obj:receivers){
            String receiver = obj.toString();
            if(receiver.contains(criteriaValue)) return true;
        }
        return false;
    }
    private boolean searchDate(JSONObject mailHeader){
        if(date == null) return false;
        Date date = (Date) mailHeader.get("date");
        if(date.compareTo(this.date) == 0) return true;
        return false;
    }
    private boolean searchBody(JSONObject mailBody ,String criteriaValue){
        String body = mailBody.toString();
        if(body.contains(criteriaValue)) return true;
        return false;
    }
    boolean searchPriority(JSONObject mailHeader){
        if(this.priority <=  0) return false;
        int priority = (int) mailHeader.get("priority");
        if(priority == this.priority) return true;
        return false;
    }
    boolean searchAttachment(String attachment, String criteriaValue){
        if(attachment.contains(criteriaValue)) return true;
        return false;
    }
    boolean found(JSONObject mail , String criteriaValue){
        JSONObject mailHeader = (JSONObject) mail.get("messageHeader");
        JSONObject mailBody = (JSONObject) mail.get("messageBody");
        String mailAttachment = mail.get("messageAttachment").toString();
        return searchSubject(mailHeader, criteriaValue) ||
                        searchSender(mailHeader, criteriaValue) ||
                        searchReceivers(mailHeader, criteriaValue)||
                        searchDate(mailHeader)||
                        searchPriority(mailHeader)||
                        searchBody(mailBody, criteriaValue)||
                        searchAttachment(mailAttachment, criteriaValue);
    }
}
