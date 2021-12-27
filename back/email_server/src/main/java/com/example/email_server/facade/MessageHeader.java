package com.example.email_server.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MessageHeader {
    //instances will contain header information
    //(to, from, subject, etc.) for an email message
    String sender; //from
    List<String> receivers; //to
    String subject;
    int priority;
    Date date;

    public MessageHeader(String sender, List<String> receivers, String subject, int priority, Date date) {
        this.sender = sender;
        this.receivers = receivers;
        this.subject = subject;
        this.priority = priority;
        this.date = date;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
