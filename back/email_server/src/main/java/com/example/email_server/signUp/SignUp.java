package com.example.email_server.signUp;
import com.example.email_server.UsersDatabase;
import com.example.email_server.file.JsonFile;
import org.json.simple.parser.*;
import java.io.File;
import java.io.IOException;
public class SignUp implements ISignUp{

    private String userName = "";
    private String password = "";
    String path = "C:\\Users\\maria\\Desktop\\users";
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
}
