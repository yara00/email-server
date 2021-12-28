package com.example.email_server.controllers;

import com.example.email_server.IMail;
import com.example.email_server.Mail;
import com.example.email_server.UsersDatabase;
import com.example.email_server.facade.MessageCreator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

@RestController
@RequestMapping("/mail")
public class MailController {
    /*
    String sender, Queue<String> receivers, String subject, int priority,
    String body, List<String> attachments*/
    @PostMapping("/send")
    boolean send(@RequestParam("subject") String subject,
              @RequestParam("sender") String sender,
              @RequestParam("receivers") String receivers,
              @RequestParam("priority") int priority,
              @RequestParam("body") String body
                 ) throws IOException, ParseException {
        UsersDatabase users = UsersDatabase.getInstance();
        String [] yourArray = receivers.split(",");
        Queue<String> checkingQueue = new LinkedList<>(Arrays.asList(yourArray));
        Queue<String> queue = new LinkedList<>(Arrays.asList(yourArray));
        while(!checkingQueue.isEmpty()){
            if(users.exists(checkingQueue.peek()))
                queue.add(checkingQueue.remove());
            else
                return false;
        }
        MessageCreator messageCreator = new MessageCreator();
        JSONObject message =(JSONObject) messageCreator.createMessage(sender, receivers, subject, priority, body ,null);
        IMail mail = new Mail();
        mail.add(sender,"Sent", message);
        while(!queue.isEmpty())
            mail.add(queue.remove(), "Inbox", message);
        return true;
    }
    @PostMapping("/move")
    void move(@RequestParam("username") String userName,
              @RequestParam("fromFile") String fromFile,
              @RequestParam("toFile") String toFile,
              @RequestBody JSONObject messageArr) throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(messageArr.toString());
        JSONArray messages = (JSONArray) object.get("users");
        IMail mail = new Mail();
        for(Object obj : messages){
            JSONObject message = (JSONObject) obj;
            mail.add(userName, toFile, message);
            mail.delete(userName, fromFile, message);
        }
    }
    @DeleteMapping("/delete")
    void delete(@RequestParam("username") String userName,
                @RequestParam("fileName") String fileName,
                @RequestBody JSONObject messagesArr) throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(messagesArr.toString());
        JSONArray messages = (JSONArray) object.get("users");
        System.out.println(messages);
        IMail mail = new Mail();
        for(Object obj : messages){
            JSONObject message = (JSONObject) obj;
            mail.delete(userName, fileName, message);
            if(!fileName.equals("Trash")){
                mail.add(userName, "Trash", message);
            }
        }
    }
}
