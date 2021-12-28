package com.example.email_server.controllers;

import com.example.email_server.filter.CriteriaAll;
import com.example.email_server.filter.CriteriaSender;
import com.example.email_server.filter.CriteriaSubject;
import com.example.email_server.filter.ICriteria;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;

@RestController
@RequestMapping("/filter")
public class FilterController {
    @GetMapping("/search")
    JSONArray search(@RequestParam("username") String userName,
                     @RequestParam("key") String key) throws ParseException, IOException {
        ICriteria criteria = new CriteriaAll();
        return searchAllFiles(userName,key,  criteria);
    }
    @GetMapping("/sender")
    JSONArray filterBySender(@RequestParam("username") String userName,
                             @RequestParam("key") String key) throws IOException, ParseException {
        ICriteria criteria = new CriteriaSender();
        return searchAllFiles(userName,key,  criteria);
    }
    @GetMapping("/subject")
    JSONArray filterBySubject(@RequestParam("username") String userName,
                             @RequestParam("key") String key) throws IOException, ParseException {
        ICriteria criteria = new CriteriaSubject();
        return searchAllFiles(userName,key,  criteria);
    }


    public static String removeFileExtension(String filename, boolean removeAllExtensions) {
        if (filename == null || filename.isEmpty()) {
            return filename;
        }

        String extPattern = "(?<!^)[.]" + (removeAllExtensions ? ".*" : "[^.]*$");
        return filename.replaceAll(extPattern, "");
    }

    public JSONArray searchAllFiles(String userName, String key, ICriteria criteria) throws IOException, ParseException {
        File folder = new File("C:\\Users\\Dell\\Desktop\\users\\" + userName);
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
