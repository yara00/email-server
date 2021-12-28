package com.example.email_server.controllers;

import com.example.email_server.contacts.Contact;
import com.example.email_server.contacts.IContact;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/contact")
public class ContactsController {
    @PostMapping("/add")
    boolean add(@RequestBody JSONObject newContact,
                @RequestParam("username") String username) throws IOException, ParseException {
        System.out.println("user"+username);
        System.out.println("d5alt"+newContact);
        IContact contact = new Contact(username);
        return contact.add(newContact);
    }
/*    @PostMapping("/edit")
    boolean edit(@RequestParam("username") String userName,
                @RequestBody JSONObject contacts) throws IOException, ParseException {
        IContact contact = new Contact(userName);
        return contact.edit(newContact);
    }*/
    @DeleteMapping("/delete")
     void delete(@RequestParam("username") String userName,
                 @RequestBody JSONObject contactToDelete) throws IOException, ParseException {
        IContact contact = new Contact(userName);
        contact.delete(contactToDelete);
    }
    @GetMapping("/load")
    JSONArray load(@RequestParam("username") String userName) throws IOException, ParseException {
        System.out.println("user is"+userName);
        IContact contact = new Contact(userName);
        return contact.loadContacts();
    }

}
