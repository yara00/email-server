package com.example.email_server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MailOperations implements IMailOperations{

    private String path = "C:\\Users\\maria\\Desktop\\users";
    @Override
    public void add(String fileName, JSONObject message) {
        JSONParser parser = new JSONParser();
        JSONObject object = null;
        try {
            object = (JSONObject) parser.parse(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = (JSONArray) object.get(fileName);
        jsonArray.add(message);
        JSONObject file = new JSONObject();
        file.put(fileName, jsonArray);
        try(FileWriter  jsonFile = new FileWriter(path)){
            jsonFile.write(file.toString());
            jsonFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
     public void delete(String userName, String deleteFromFile, JSONObject message){
        String path = this.path + "\\" + userName +"\\" + deleteFromFile + "\\" + deleteFromFile +".json";
        JSONParser parser = new JSONParser();
         JSONObject object = null;
         try {
             object = (JSONObject) parser.parse(new FileReader(path));
         } catch (IOException e) {
             e.printStackTrace();
         } catch (ParseException e) {
             e.printStackTrace();
         }
         JSONArray jsonArray = (JSONArray) object.get(deleteFromFile);
        for(int i = 0; i < jsonArray.size(); i++){
            JSONObject match = (JSONObject) jsonArray.get(i);
            if(match.equals(message)){
                jsonArray.remove(i);
            }
        }
        JSONObject file = new JSONObject();
        file.put(deleteFromFile, jsonArray);
        try(FileWriter  jsonFile = new FileWriter(path)){
            jsonFile.write(file.toString());
            jsonFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
