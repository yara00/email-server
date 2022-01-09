package com.example.email_server.sorting;

import com.example.email_server.facade.Message;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

public class Sorter {
    public JSONObject sort(String userName, String fileName, String criteria, int page) throws IOException, ParseException {
        String filePath = "C:\\Users\\Dell\\Desktop\\users" + "\\" + userName + "\\" + fileName + ".json";
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
        System.out.println("ss"+jsonObject);
        // JSONArray msgArr = (JSONArray) jsonObject.get("Inbox");
        JSONArray msgArr = new JSONArray();
        ISort sorted = null;
        System.out.println(criteria);
        if (criteria.equalsIgnoreCase("body")) {
            msgArr = (JSONArray) jsonObject.get(fileName);
            sorted = new SortBody();
        }
        else if(criteria.equalsIgnoreCase("subject")) {
            msgArr = (JSONArray) jsonObject.get(fileName);
            sorted = new SortSubject();
        }
        else if(criteria.equalsIgnoreCase("importance")) {
            msgArr = (JSONArray) jsonObject.get(fileName);
            sorted = new SortPriority();
        }
        else if(criteria.equalsIgnoreCase("sender")) {
            msgArr = (JSONArray) jsonObject.get(fileName);
            sorted = new SortSender();
        }
        else if(criteria.equalsIgnoreCase("date")) {
            msgArr = (JSONArray) jsonObject.get(fileName);
            sorted = new SortDate();
        }
        else if(criteria.equalsIgnoreCase("name")) {
            msgArr = (JSONArray) jsonObject.get("Contacts");
            sorted = new SortName();
        }
        System.out.println("msg is"+msgArr);
        Collections.sort(msgArr, sorted);
        JSONArray arr = msgArr;
        JSONArray messagesToSent = new JSONArray();
        int start = (page * 10);
        int end = start + 10;
        for(int i = start; i < end && i < arr.size(); i++){
            messagesToSent.add(arr.get(i));
        }
        JSONObject object  = new JSONObject();
        object.put("messagesNo", arr.size());
        object.put("mails",messagesToSent);
        return object;
    }
}
