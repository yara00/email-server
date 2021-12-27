package com.example.email_server.file;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFile {

    public JsonFile(String fileName, String folderName) {
        this.fileName = fileName;
        this.folderName = folderName;
    }

    private String fileName = "";
    private String folderName ="";

    public void create(){
        String path = "C:\\Users\\maria\\Desktop\\users\\" + folderName + "//"+ fileName + ".json";
        JSONObject jsonFile = new JSONObject();
        if(!fileName.equalsIgnoreCase("info")){
            JSONArray jsonArray = new JSONArray();
            jsonFile.put(fileName,jsonArray);
        }
        try(FileWriter userFile = new FileWriter(path)){
            userFile.write(jsonFile.toString());
            userFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
