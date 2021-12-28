package com.example.email_server;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IMail {
    public void add(String userName,String fileName, JSONObject message) throws IOException, ParseException;
    public void delete(String userName, String deleteFromFile , JSONObject message) throws IOException, ParseException;
}
