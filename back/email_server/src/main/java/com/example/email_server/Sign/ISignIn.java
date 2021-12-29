package com.example.email_server.Sign;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ISignIn {
    public boolean verifyCredentials(String userName, String password) throws IOException, ParseException;

}
