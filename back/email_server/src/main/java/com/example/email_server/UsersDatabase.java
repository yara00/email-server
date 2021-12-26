package com.example.email_server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class UsersDatabase {
    private static UsersDatabase instance = null;
    private static String path = "C:\\Users\\maria\\Desktop\\users\\Users.json";
    private static JSONArray usersList = new JSONArray();

    private UsersDatabase() {

    }
    public static UsersDatabase getInstance() throws IOException, ParseException {
        if(instance == null){
            instance =  new UsersDatabase();
            File findFile = new File(path);
            if(!findFile.exists()){
                JSONObject file = new JSONObject();
                file.put("users", usersList);
                try(FileWriter usersFile = new FileWriter(path)){
                    usersFile.write(file.toString());
                    usersFile.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                JSONParser parser = new JSONParser();
                JSONObject object = (JSONObject) parser.parse(new FileReader(path));
                usersList = (JSONArray) object.get("users");
            }
        }
        return instance;
    }
    public void addUser(String userName, String password){
        JSONObject user = new JSONObject();
        user.put("userName", userName);
        user.put("password", password);
        usersList.add(user);
        JSONObject file = new JSONObject();
        file.put("users", usersList);
        try(FileWriter usersFile = new FileWriter(path)){
            usersFile.write(file.toString());
            usersFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean exists(String userName){
        for(Object obj : usersList){
            JSONObject user = (JSONObject) obj;
            if(user.get("userName").equals(userName)){
                return true;
            }
        }
        return false;
    }
    public static JSONArray getUsersList() {
        return usersList;
    }



}
