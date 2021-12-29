package com.example.email_server.Sign;

import com.example.email_server.UsersDatabase;
import org.json.JSONException;
import org.json.simple.*;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class SignInProxy implements ISignIn {
    @Override
    public boolean verifyCredentials(String userName, String password) throws IOException, ParseException {
        Sign user = new Sign();
        if(user.verifyCredentials(userName, password)) return true;
        return false;
    }

}
