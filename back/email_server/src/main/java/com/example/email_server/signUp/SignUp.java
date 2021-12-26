package com.example.email_server.signUp;
import com.example.email_server.UsersDatabase;
import org.json.JSONException;
import org.json.simple.parser.*;
import org.json.simple.*;
import java.io.File;
import java.io.FileWriter;
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
    public void createUserFolders() {

        String path = this.path + "//" + this.userName;
        createFile("info", path);
        path = path + "//";
        createFile("Inbox", path + "Inbox");
        createFile("Trash", path + "Trash");
        createFile("Draft", path + "Draft");
        createFile("Contacts", path + "Contacts");
        createFile("Sent", path + "Sent");
    }
    private void createFile(String fileName, String path){
        File file =  new File(path);
        file.mkdir();
        JSONObject jsonFile = new JSONObject();
        if(!fileName.equalsIgnoreCase("info")){
            JSONArray jsonArray = new JSONArray();
            jsonFile.put(fileName,jsonArray);
        }
        try(FileWriter userFile = new FileWriter(path +"//"+ fileName +".json")){
            userFile.write(jsonFile.toString());
            userFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject userInfo() throws JSONException {
        JSONObject info = new JSONObject();
        info.put("userName", this.userName);
        info.put("password", this.password);
        try(FileWriter infoFile = new FileWriter(this.path + "\\"+ this.userName +"\\"+ "info.json")){
            infoFile.write(info.toString());
            infoFile.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return info;
    }

}
