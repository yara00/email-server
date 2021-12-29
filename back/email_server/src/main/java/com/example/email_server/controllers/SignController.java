package com.example.email_server.controllers;

import com.example.email_server.Sign.ISignIn;
import com.example.email_server.Sign.Sign;
import com.example.email_server.Sign.SignInProxy;
import com.example.email_server.Sign.ISignUp;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sign")
public class SignController {
    @PostMapping("/signup")
    boolean signUp(@RequestParam("userName") String userName,
                   @RequestParam("password") String password) throws IOException, ParseException {
        ISignUp user = new Sign();
        user.setUserName(userName);
        user.setPassword(password);
        if(!user.exists()){
            user.createUserFolders();
            return true;
        }
        return false;
    }
    @GetMapping("/signin")
    boolean signIn(@RequestParam("userName") String userName,
                     @RequestParam("password") String password) throws IOException, ParseException {
        ISignIn user = new SignInProxy();
        return user.verifyCredentials(userName, password);
    }
}
