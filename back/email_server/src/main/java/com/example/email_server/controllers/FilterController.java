package com.example.email_server.controllers;

import com.example.email_server.Folder;
import com.example.email_server.IFolder;
import com.example.email_server.filter.CriteriaAll;
import com.example.email_server.filter.CriteriaSender;
import com.example.email_server.filter.CriteriaSubject;
import com.example.email_server.filter.ICriteria;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Path;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/filter")
public class FilterController {

    @GetMapping("/search")
    JSONObject search(@RequestParam("userName") String userName,
                     @RequestParam("key") String key,
                     @RequestParam("page") int page) throws ParseException, IOException {
        ICriteria criteria = new CriteriaAll();
        JSONArray allMessages = searchAllFiles(userName, key,  criteria);
        JSONArray results = new JSONArray();
        int start = allMessages.size() - (page * 10) -1;
        int end = start - 10;
        for(int i = start; i > end && i > -1; i--){
            results.add(allMessages.get(i));
        }
        JSONObject load = new JSONObject();
        load.put("messagesNo", allMessages.size());
        load.put("content", results);
        return load;
    }
    @GetMapping("/sender")
    boolean filterBySender(@RequestParam("userName") String userName,
                             @RequestParam("fileName") String fileName,
                             @RequestParam("key") String key) throws IOException, ParseException {
        ICriteria criteria = new CriteriaSender();
        IFolder  folder = new Folder(userName, fileName);
        String path = "C:\\Users\\maria\\Desktop\\users\\" + userName+ "\\" + fileName + ".json";
        if(!folder.verify()) return false;
        folder.create();
        saveFilteredMessages(userName, fileName,  searchAllFiles(userName,key,criteria), path);
        return true;
    }
    @GetMapping("/subject")
    boolean filterBySubject(@RequestParam("userName") String userName,
                             @RequestParam("fileName") String fileName,
                             @RequestParam("key") String key) throws IOException, ParseException {
        ICriteria criteria = new CriteriaSubject();
        IFolder  folder = new Folder(userName, fileName);
        String path = "C:\\Users\\maria\\Desktop\\users\\" + userName+ "\\" + fileName + ".json";
        if(!folder.verify()) return false;
        folder.create();
        saveFilteredMessages(userName, fileName,  searchAllFiles(userName,key,criteria), path);
        return true;
    }

    private void saveFilteredMessages(String userName, String fileName, JSONArray mails, String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object =(JSONObject) parser.parse(new FileReader(path));
        JSONArray jsonArray = (JSONArray) object.get("fileName");
        for(int i = 0; i < jsonArray.size(); i++){
            mails.add(jsonArray.get(i));
        }
        JSONObject file = new JSONObject();
        file.put(fileName, mails);
        FileWriter  writer = new FileWriter(path);
        writer.write(file.toString());
        writer.flush();
    }

    public static String removeFileExtension(String filename, boolean removeAllExtensions) {
        if (filename == null || filename.isEmpty()) {
            return filename;
        }

        String extPattern = "(?<!^)[.]" + (removeAllExtensions ? ".*" : "[^.]*$");
        return filename.replaceAll(extPattern, "");
    }

    public JSONArray searchAllFiles(String userName, String key, ICriteria criteria) throws IOException, ParseException {
        File folder = new File("C:\\Users\\maria\\Desktop\\users\\" + userName);
        File[] listOfFiles = folder.listFiles();
        JSONArray results = new JSONArray();
        for(File file : listOfFiles){
            JSONParser parser = new JSONParser();
            String fileName = removeFileExtension(file.getName(), false);
            if(fileName.equals("ExtraFiles")) continue;
            JSONObject object = (JSONObject) parser.parse(new FileReader(String.valueOf(file)));
            JSONArray arr = (JSONArray) object.get(fileName);
            JSONArray fileArr =  criteria.meetCriteria(arr, key);
            for(Object obj : fileArr){
                results.add(obj);
            }
        }
        return results;
    }


}
