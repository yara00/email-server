package com.example.email_server.facade;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Queue;

public class MessageHeader {
    //instances will contain header information
    //(to, from, subject, etc.) for an email message
    String sender; //from
    Queue<String> receivers; //to
    String subject;
    int priority;
    String date;

    public MessageHeader(String sender, Queue<String> receivers, String subject, int priority, String date) {
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

    public Queue<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(Queue<String> receivers) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
