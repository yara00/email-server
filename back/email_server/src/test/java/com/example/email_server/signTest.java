package com.example.email_server;

import com.example.email_server.Sign.ISignIn;
import com.example.email_server.Sign.ISignUp;
import com.example.email_server.Sign.Sign;
import com.example.email_server.Sign.SignInProxy;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class signTest {
    @Test
    public void signInTestTrue() throws IOException, ParseException {
        /*ICriteria senderCriteria = new CriteriaSender();
        JSONArray jsonArray = senderCriteria.meetCriteria()

        Sorter sorter = new Sorter();
        JSONObject jsonObject = sorter.sort("marioma","Sent","priority", 1);
        System.out.println("json is "+jsonObject);
        */
        ISignIn user = new SignInProxy();
        boolean test = user.verifyCredentials("marioma", "12345");
        Assert.assertEquals(true, test);
    }
    @Test
    public void signInTestFalse() throws IOException, ParseException {
        /*ICriteria senderCriteria = new CriteriaSender();
        JSONArray jsonArray = senderCriteria.meetCriteria()

        Sorter sorter = new Sorter();
        JSONObject jsonObject = sorter.sort("marioma","Sent","priority", 1);
        System.out.println("json is "+jsonObject);
        */
        ISignIn user = new SignInProxy();
        boolean test = user.verifyCredentials("hello", "12345");
        Assert.assertEquals(false, test);
    }

    @Test
    public void signUpTestTrue() throws IOException, ParseException {
        /*ICriteria senderCriteria = new CriteriaSender();
        JSONArray jsonArray = senderCriteria.meetCriteria()

        Sorter sorter = new Sorter();
        JSONObject jsonObject = sorter.sort("marioma","Sent","priority", 1);
        System.out.println("json is "+jsonObject);
        */
        ISignUp user = new Sign();
        user.setUserName("yara");
        user.setPassword("password");
        boolean test = false;
        if(!user.exists()){
            user.createUserFolders();
             test = true;
        }

        Assert.assertEquals(true, test);
    }
    @Test
    public void signUpTestFalse() throws IOException, ParseException {
        /*ICriteria senderCriteria = new CriteriaSender();
        JSONArray jsonArray = senderCriteria.meetCriteria()

        Sorter sorter = new Sorter();
        JSONObject jsonObject = sorter.sort("marioma","Sent","priority", 1);
        System.out.println("json is "+jsonObject);
        */
        ISignUp user = new Sign();
        user.setUserName("marioma");
        user.setPassword("12345");
        boolean test = false;
        if(!user.exists()){
            user.createUserFolders();
            test = true;
        }

        Assert.assertEquals(false, test);
    }
}