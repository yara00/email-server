package com.example.email_server;

import org.json.simple.JSONObject;

public interface IMailOperations {
    public void add(String fileName, JSONObject message);
    public void delete(String userName, String deleteFromFile , JSONObject message);
}
