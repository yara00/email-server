package com.example.email_server;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface IFolder {
    boolean create();
    boolean rename(String name) throws IOException, ParseException;
    void delete() throws IOException, ParseException;
    public JSONArray loadMessages(int page) throws IOException, ParseException;
}
