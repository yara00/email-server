package com.example.email_server.signIn;

import com.example.email_server.UsersDatabase;
import org.json.JSONException;
import org.json.simple.*;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class SignInProxy implements ISignIn{
    private boolean verifyCredentials(String userName, String password) throws IOException, ParseException, JSONException {
        UsersDatabase usersDatabase = UsersDatabase.getInstance() ;
        JSONArray usersList = usersDatabase.getUsersList();
        for(Object obj : usersList){
            JSONObject user = (JSONObject) obj;
            if(user.get("userName").equals(userName) && user.get("password").equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONArray signIn(String userName, String password)  {
        try {
            boolean verify = verifyCredentials(userName, password);
            if(verify){
                SignIn user = new SignIn();
                return user.signIn(userName, password);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }



}
