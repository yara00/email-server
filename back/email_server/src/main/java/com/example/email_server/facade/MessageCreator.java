package com.example.email_server.facade;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import java.util.*;

public class MessageCreator {
    public JSONObject createMessage(String sender, String receivers, String subject, int priority,
                                    String body, List<String> attachments) throws JsonProcessingException {
        Date currDate = new Date();
        String date = currDate.toString();
        MessageHeader header = new MessageHeader(sender, receivers, subject, priority, date);
        MessageBody body1 = new MessageBody(body);
        MessageAttachment attachment = new MessageAttachment(attachments);

        Message message = new Message(header,body1,attachment);
        JSONObject jsonObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        headerObject.put("sender", sender);
        headerObject.put("receivers", receivers);
        headerObject.put("subject", subject);
        headerObject.put("priority", priority);
        headerObject.put("date", date);
        jsonObject.put("messageHeader", headerObject);
        JSONObject bodyObject = new JSONObject();
        bodyObject.put("body", body);
        jsonObject.put("messageBody", bodyObject);
        JSONObject attachmentObject = new JSONObject();
        attachmentObject.put("attachment", attachmentObject);
        jsonObject.put("messageAttachment", attachments);
        System.out.println(jsonObject);
        return jsonObject;
    }
}
