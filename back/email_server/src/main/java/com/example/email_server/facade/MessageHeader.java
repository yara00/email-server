package com.example.email_server.facade;

import java.util.Calendar;
import java.util.List;

public class MessageHeader {
    //instances will contain header information
    //(to, from, subject, etc.) for an email message
    String sender; //from
    List<String> receivers; //to
    String subject;
    int priority;
    Calendar date;
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
