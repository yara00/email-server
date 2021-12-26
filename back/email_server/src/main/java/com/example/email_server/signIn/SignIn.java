package com.example.email_server.signIn;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class SignIn implements ISignIn{
    private String path = "C:\\Users\\maria\\Desktop\\users\\";
    public JSONObject signIn(String userName, String password){
        JSONParser parser = new JSONParser();
        String path =  userName+ "\\info.json";
        try {
            JSONObject info = (JSONObject) parser.parse(new FileReader(this.path + path));
            return info;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
