package com.example.email_server.controllers;

import com.example.email_server.signIn.ISignIn;
import com.example.email_server.signIn.SignIn;
import com.example.email_server.signIn.SignInProxy;
import com.example.email_server.signUp.ISignUp;
import com.example.email_server.signUp.SignUp;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/sign")
public class SignController {
    @PostMapping("/signup")
    boolean signUp(@RequestParam("userName") String userName,
                   @RequestParam("password") String password) throws IOException, ParseException {
        ISignUp user = new SignUp();
        user.setUserName(userName);
        user.setPassword(password);
        if(!user.exists()){
            user.createUserFolders();
            return true;
        }
        return false;
    }
    @GetMapping("/signin")
    JSONArray signIn(@RequestParam("userName") String userName,
                     @RequestParam("password") String password) throws IOException, ParseException {
        ISignIn user = new SignInProxy();
        return user.signIn(userName, password);
    }
}
