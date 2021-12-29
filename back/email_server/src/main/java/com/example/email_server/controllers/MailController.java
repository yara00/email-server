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
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
                 @RequestParam("body") String body,
                 @RequestParam("attachment") String[] attachment) throws IOException, ParseException {
        receivers = "marioma";
        System.out.println("disgggggggggggggggggggggggggggggnfbue");
        System.out.println(Arrays.asList(attachment) +"here");
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
        System.out.println("Ahhhhhh");
        System.out.println(sender+receivers+subject+priority+body+Arrays.asList(attachment));
        List<String> a = new ArrayList<>();
        for (String s : attachment) {
            a.add(s);
        }
        System.out.println("A IS "+a);
        JSONObject message = (JSONObject) messageCreator.createMessage("marioma", "marioma", subject, priority, body ,a);
        System.out.println("me is"+message);
        IMail mail = new Mail();
        mail.add("marioma","Sent", message);
        while(!queue.isEmpty())
            mail.add(queue.remove(), "Inbox", message);
        return true;
    }
    @PostMapping("/move")
    void move(@RequestParam("userName") String userName,
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
    void delete(@RequestParam("userName") String userName,
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
