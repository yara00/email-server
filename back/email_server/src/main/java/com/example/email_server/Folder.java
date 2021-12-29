package com.example.email_server;

import com.example.email_server.file.JsonFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.*;

public class Folder implements IFolder {
    public Folder(String userName, String folderName) {
        this.folderName = folderName;
        this.userName = userName;
        try {
            extraFiles = loadArray("ExtraFiles");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private String[] forbiddenFileNames = {"Inbox", "Contacts", "Draft", "Trash", "ExtraFiles","Sent"};
    private String folderName = "";
    private String userName = "";
    private String path = "C:\\Users\\maria\\Desktop\\users\\";
    private JSONArray extraFiles;

    public int getMessagesNo() {
        return messageNo;
    }

    private int messageNo = 0;

    @Override
    public boolean create(){
        if(!verify()) return false;
        for(Object obj : extraFiles){
            JSONObject file = (JSONObject) obj;
            System.out.print(extraFiles);
            if(file.get("fileName").equals(this.folderName))
            {
                return false;
            }
        }
        JsonFile jsonFile = new JsonFile(this.folderName, this.userName);
        jsonFile.create();
        JSONObject newFile = new JSONObject();
        newFile.put("fileName", this.folderName);
        extraFiles.add(newFile);
        writeArray("ExtraFiles", this.extraFiles);
        return true;
    }


    @Override
    public boolean rename(String name) throws IOException, ParseException {
        if(!verify()) return false;
        int prevFile = -1;
        for(int i = 0 ; i < this.extraFiles.size(); i++){
            JSONObject file = (JSONObject) this.extraFiles.get(i);
            if(file.get("fileName").equals(this.folderName)){
                prevFile = i;
            }
            if(file.get("fileName").equals(name)){
                return false;
            }
        }
        extraFiles.remove(prevFile);
        JSONObject newFile = new JSONObject();
        newFile.put("fileName", name);
        this.extraFiles.add(newFile);
        writeArray("ExtraFiles", this.extraFiles);
        Path source = Paths.get(this.path + this.userName +"\\" + this.folderName +".json");
        Files.move(source, source.resolveSibling(name +".json"), StandardCopyOption.REPLACE_EXISTING);
        JSONParser parser =new JSONParser();
        JSONObject object = (JSONObject) parser.parse(new FileReader(this.path + this.userName +"\\" + name +".json"));
        FileWriter writer = new FileWriter(this.path + this.userName +"\\" + name +".json");
        JSONObject file = new JSONObject();
        file.put(name, (JSONArray) object.get(this.folderName));
        writer.write(file.toString());
        writer.flush();
        return true;
    }

    @Override
    public void delete() throws IOException, ParseException {
        File file = new File(this.path + this.userName + "\\" + this.folderName+".json");
        System.out.print(this.path + this.userName + "\\" + this.folderName+"json");
        if (file.delete()){
        }
        for(int i = 0 ; i < this.extraFiles.size(); i++){
            JSONObject fileToDelete = (JSONObject) this.extraFiles.get(i);
            if(fileToDelete.get("fileName").equals(this.folderName)){
                this.extraFiles.remove(i);
            }
        }
        writeArray("ExtraFiles", this.extraFiles);
    }
    public JSONArray loadFiles() throws IOException, ParseException {
        return extraFiles;
    }
    public boolean verify(){
        for(String name : this.forbiddenFileNames){
            if(this.folderName.equalsIgnoreCase(name));
            return false;
        }
        return true;
    }

    private JSONArray loadArray(String arrayName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(new FileReader(this.path + this.userName +"\\" + arrayName +".json"));
        return  (JSONArray) object.get(arrayName);

    }
    private void writeArray(String arrayName, JSONArray jsonArray) {
        JSONObject file = new JSONObject();
        file.put(arrayName, jsonArray);
        FileWriter jsonFile = null;
        try {
            jsonFile = new FileWriter(this.path + this.userName + "\\" + arrayName + ".json");
            jsonFile.write(file.toString());
            jsonFile.flush();
            jsonFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
