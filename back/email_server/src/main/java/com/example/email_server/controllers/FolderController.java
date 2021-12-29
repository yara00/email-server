package com.example.email_server.controllers;
import com.example.email_server.Folder;
import com.example.email_server.IFolder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/folder")
public class FolderController {
    @PostMapping("/create")
    boolean create(@RequestParam("userName") String userName,
                   @RequestParam("fileName") String fileName){
        IFolder folder = new Folder(userName, fileName);
        return folder.create();
    }

    @PostMapping("/rename")
    boolean rename(@RequestParam("userName") String userName,
                   @RequestParam("fileName") String fileName,
                   @RequestParam("newName") String newName){
        IFolder folder = new Folder(userName, fileName);
        try {
            return folder.rename(newName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
    @DeleteMapping("/delete")
    void delete(@RequestParam("userName") String userName,
                   @RequestParam("fileName") String fileName) throws IOException, ParseException {
        IFolder folder = new Folder(userName, fileName);
        folder.delete();
    }
    @GetMapping("/load")
    JSONArray load(@RequestParam("userName") String userName) throws IOException, ParseException {
        IFolder folder = new Folder(userName, "");
       return folder.loadFiles();
    }

}
