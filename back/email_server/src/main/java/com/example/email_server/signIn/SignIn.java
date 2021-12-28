package com.example.email_server.signIn;

import com.example.email_server.Folder;
import com.example.email_server.IFolder;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class SignIn implements ISignIn {
    private String path = "C:\\Users\\maria\\Desktop\\users\\";

    public JSONArray signIn(String userName, String password) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        IFolder folder = new Folder(userName, "Inbox");
        return folder.loadMessages(1);
    }
}
