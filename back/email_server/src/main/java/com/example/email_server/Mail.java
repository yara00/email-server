package com.example.email_server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Mail implements IMail {

    private String path = "C:\\Users\\Dell\\Desktop\\users";
    @Override
    public void add(String userName,String fileName, JSONObject message) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        String path = this.path + "\\"+ userName +"\\" + fileName  +".json";
        System.out.print(path);
        JSONObject object = (JSONObject) parser.parse(new FileReader(path));
        JSONArray jsonArray = (JSONArray) object.get(fileName);
        jsonArray.add(message);
        JSONObject file = new JSONObject();
        file.put(fileName, jsonArray);
        FileWriter  jsonFile = new FileWriter(path);
        jsonFile.write(file.toString());
        jsonFile.flush();

    }

    @Override
     public void delete(String userName, String deleteFromFile, JSONObject message) throws IOException, ParseException {
        String path = this.path + "\\" + userName +"\\" + deleteFromFile  +".json";
        JSONParser parser = new JSONParser();
         JSONObject object = (JSONObject) parser.parse(new FileReader(path));
         JSONArray jsonArray = (JSONArray) object.get(deleteFromFile);
        for(int i = 0; i < jsonArray.size(); i++){
            JSONObject match = (JSONObject) jsonArray.get(i);
            if(match.equals(message)){
                jsonArray.remove(i);
            }
        }
        JSONObject file = new JSONObject();
        file.put(deleteFromFile, jsonArray);
        FileWriter  jsonFile = new FileWriter(path);
        jsonFile.write(file.toString());
        jsonFile.flush();

    }
}
