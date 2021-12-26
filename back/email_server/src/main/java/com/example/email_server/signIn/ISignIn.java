package com.example.email_server.signIn;

import org.json.simple.JSONObject;

public interface ISignIn {
    public JSONObject signIn(String userName, String password);

}
