package com.example.email_server.controllers;

import com.example.email_server.contacts.Contact;
import com.example.email_server.contacts.IContact;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin()
@RequestMapping("/contact")
public class ContactsController {
    @PostMapping("/add")
    boolean add(@RequestParam("userName") String userName,
                @RequestBody JSONObject newContact) throws IOException, ParseException {
        IContact contact = new Contact(userName);
        return contact.add(newContact);
    }
    @PostMapping("/edit")
    boolean edit(@RequestBody JSONArray contacts,
                 @RequestParam ("userName") String userName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray obj = (JSONArray)parser.parse(contacts.toString());
        JSONObject prev = (JSONObject)obj.get(1);
        JSONObject curr = (JSONObject) obj.get(0);
        IContact contact = new Contact(prev.get("name").toString());
        return contact.edit(prev, curr);
    }

    @DeleteMapping("/delete")
     void delete(@RequestParam("userName") String userName,
                 @RequestBody JSONObject contactToDelete) throws IOException, ParseException {
        IContact contact = new Contact(userName);
        contact.delete(contactToDelete);
    }
    @GetMapping("/load")
    JSONArray load(@RequestParam("userName") String userName) throws IOException, ParseException {
        IContact contact = new Contact(userName);
        return contact.loadContacts();
    }


}
