package com.example.email_server.facade;

import com.example.email_server.sorting.SortBody;
import com.example.email_server.sorting.SortDate;
import com.example.email_server.sorting.SortPriority;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MessageCreator {
    ArrayList<Message> msgArr = new ArrayList<>();
    public Object createMessage() throws JsonProcessingException {
        Date curr = new Date();
        System.out.println("curr is"+curr);
        GregorianCalendar calendar = new GregorianCalendar();
        System.out.println("timeis"+calendar.getTime());
        List<String> receivers = new ArrayList<>();
        receivers.add("yaryora@gmail.com");
        receivers.add("marioma@gmail.com");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();

        MessageHeader header = new MessageHeader("lala@gmail.com", receivers, "Email subject", 3,curr);//dateFormat.format(cal.getTime()));
        MessageBody body = new MessageBody("TESTING");
        MessageAttachment newAttachment = new MessageAttachment();

        Message newMessage = new Message(header, body, newAttachment);

        msgArr.add(newMessage);
        System.out.println("hena"+dateFormat.format(cal.getTime()));
     //   System.out.println(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
        cal = Calendar.getInstance();
        MessageHeader header2 = new MessageHeader("zala@gmail.com", receivers, "Amail subject", 1, curr);//dateFormat.format(cal.getTime()));
        MessageBody body2 = new MessageBody("AESTING");
        MessageAttachment newAttachment2 = new MessageAttachment();

        Message newMessage2 = new Message(header2, body2, newAttachment2);
        msgArr.add(newMessage2);

        System.out.println("ARR IS"+ msgArr);


        MessageHeader header3 = new MessageHeader("", receivers, "", 2, curr);
        //("zala@gmail.com", receivers, "Amail subject", 1, dateFormat.format(cal.getTime()));
        MessageBody body3 = new MessageBody("CESTING");
        MessageAttachment newAttachment3 = new MessageAttachment();

        Message newMessage3 = new Message(header3, body3, newAttachment3);
        msgArr.add(newMessage3  );
        ArrayList<Message> msgArr2 = msgArr;
        System.out.println("ARR IS"+ msgArr);

        //  System.out.println("LALA"+newMessage.getMessageHeader().getSender().compareTo());
        ObjectMapper org = new ObjectMapper();
        System.out.println(org.writerWithDefaultPrettyPrinter().writeValueAsString(newMessage));
      /*  for (int i = 0; i < msgArr.size(); i++) {
            for (int j = i + 1; j < msgArr.size(); j++) {
                if (msgArr.get(i).getMessageBody().getBody().compareToIgnoreCase(msgArr.get(j).getMessageBody().getBody()) > 0) {
                    Message temp = msgArr.get(i);
                    msgArr.set(i, msgArr.get(j));
                    msgArr.set(j,temp);
                }
            }
        }*/

            // Method
            // Sorting in ascending order of name

        System.out.println("SORTED"+msgArr);

        Collections.sort(msgArr2, new SortPriority());

        System.out.println("SORTEDPRIORIRTY"+msgArr2);
        Collections.sort(msgArr2, new SortDate());

        System.out.println("SORTEDDATE"+msgArr2);

        return org.writerWithDefaultPrettyPrinter().writeValueAsString(newMessage);
    }
}
