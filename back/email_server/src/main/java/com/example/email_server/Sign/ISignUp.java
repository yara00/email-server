package com.example.email_server.Sign;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface ISignUp {
    void createUserFolders() throws IOException, ParseException;
    void setUserName(String userName);
    void setPassword(String password);
    public boolean exists() throws IOException, ParseException;
}
