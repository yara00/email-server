package com.example.email_server.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MessageCreator {
    public Object createMessage(String sender, String receivers, String subject, int priority,
                                String body, List<String> attachments) throws JsonProcessingException {
        Date currDate = new Date();
        String date = currDate.toString();
        MessageHeader header = new MessageHeader(sender, receivers, subject, priority, date);
        MessageBody body1 = new MessageBody(body);
        MessageAttachment attachment = new MessageAttachment(attachments);

        Message message = new Message(header,body1,attachment);
        ObjectMapper org = new ObjectMapper();
        System.out.println(org.writerWithDefaultPrettyPrinter().writeValueAsString(message));
        return org.writerWithDefaultPrettyPrinter().writeValueAsString(message);
    }
}
