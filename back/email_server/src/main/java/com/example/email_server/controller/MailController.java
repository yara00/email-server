package com.example.email_server.controllers;

import com.example.email_server.IMail;
import com.example.email_server.Mail;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/mail")
public class MailController {
    @PostMapping("/send")
    void send(@RequestBody JSONObject message, @RequestParam("sender") String sender,
                 @RequestParam("receivers") String receivers
                 ) throws IOException, ParseException {
        IMail mail = new Mail();
        mail.add(sender,"Sent", message);
        mail.add(receivers, "Inbox", message);
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
