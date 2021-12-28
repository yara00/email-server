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
    public JSONArray sort(String userName, String fileName, String criteria) throws IOException, ParseException {
        String filePath = "C:\\Users\\Dell\\Desktop\\users" + "\\" + userName + "\\" + fileName;
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
        JSONArray msgArr = (JSONArray) jsonObject.get("Inbox");
        ISort sorted = null;
        if (criteria.equalsIgnoreCase("body")) {
            sorted = new SortBody();
        }
        else if(criteria.equalsIgnoreCase("subject")) {
            sorted = new SortSubject();
        }
        else if(criteria.equalsIgnoreCase("priority")) {
            sorted = new SortPriority();
        }
        else if(criteria.equalsIgnoreCase("sender")) {
            sorted = new SortSender();
        }
        else if(criteria.equalsIgnoreCase("date")) {
            sorted = new SortDate();
        }
        System.out.println("msg is"+msgArr);

        Collections.sort(msgArr, sorted);
        JSONArray arr = msgArr;
        return arr;
    }
}
