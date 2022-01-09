package com.example.email_server.Sign;
import com.example.email_server.UsersDatabase;
import com.example.email_server.file.JsonFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.io.File;
import java.io.IOException;
public class Sign implements ISignIn, ISignUp{

    private String userName = "";
    private String password = "";
    String path = "C:\\Users\\Dell\\Desktop\\users";
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean exists() throws IOException, ParseException{
        UsersDatabase usersList = UsersDatabase.getInstance();
        return usersList.exists(this.userName);
    }
    public void createUserFolders() throws IOException, ParseException {
        File file =  new File(this.path + "//" + this.userName);
        file.mkdir();
        JsonFile  inbox = new JsonFile("Inbox", this.userName);
        inbox.create();
        JsonFile  sent = new JsonFile("Sent", this.userName);
        sent.create();
        JsonFile  trash = new JsonFile("Trash", this.userName);
        trash.create();
        JsonFile  draft = new JsonFile("Draft", this.userName);
        draft.create();
        JsonFile  contacts = new JsonFile("Contacts", this.userName);
        contacts.create();
        JsonFile  extraFiles = new JsonFile("ExtraFiles", this.userName);
        extraFiles.create();
        UsersDatabase users = UsersDatabase.getInstance();
        users.addUser(userName, password);

    }
    public boolean verifyCredentials(String userName, String password) throws IOException, ParseException {
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
}
