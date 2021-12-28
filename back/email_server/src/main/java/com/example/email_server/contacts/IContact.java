package com.example.email_server.contacts;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface IContact {
    public boolean add(JSONObject contact) throws IOException, ParseException;
    public boolean edit(JSONObject newContact, JSONObject prevContact) throws IOException, ParseException;
    public void delete(JSONObject contact) throws IOException, ParseException;
    public JSONArray loadContacts() throws IOException, ParseException;
}
