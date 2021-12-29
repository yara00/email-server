package com.example.email_server.contacts;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Contact implements IContact {
    public Contact(String userName) {
        this.userName = userName;
        this.path = this.path + userName + "\\Contacts.json";
        try {
           this.contacts =  loadContacts();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private String path = "C:\\Users\\Dell\\Desktop\\users\\";

    String userName;
    private JSONArray contacts;
    public boolean add(JSONObject contact) throws IOException, ParseException {
        if(exists(contact)) return false;
        this.contacts.add(contact);
        writeContacts();
        return true;
    }
    public boolean edit(JSONObject newContact, JSONObject prevContact) throws IOException, ParseException {
        if(exists(newContact)) return false;
        this.contacts.add(newContact);
        delete(prevContact);
        writeContacts();
        return true;
    }

    public void delete(JSONObject contactToDelete) throws IOException, ParseException {
        for( int i = 0; i < this.contacts.size(); i++){
            JSONObject contact = (JSONObject) contacts.get(i);
            if(contact.equals(contactToDelete)){
               contacts.remove(i);
               writeContacts();
               return;
            }
        }
    }

    public JSONArray loadContacts() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(new FileReader(this.path));
        return  (JSONArray) object.get("Contacts");
    }
    private void writeContacts() throws IOException, ParseException {
        JSONObject file = new JSONObject();
        file.put("Contacts", contacts);
        FileWriter usersFile = new FileWriter(this.path);
        usersFile.write(file.toString());
        usersFile.flush();
    }
    private boolean exists(JSONObject contact) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(contact.toString());
        JSONArray emailsToSet = (JSONArray) object.get("emails");
        for(Object obj: contacts){
            JSONArray emails = (JSONArray) ((JSONObject)obj).get("emails");
            for(Object emailObject : emails){
                for(Object emailToSetObject : emailsToSet){
                    String email = (String) emailObject;
                    String emailToSet = emailToSetObject.toString();
                    if(email.equals(emailToSet)) return true;
                }
            }
        }
        return false;
    }


}
