package com.example.email_server.controllers;

import com.example.email_server.sorting.Sorter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sort")
public class SortController {
    @GetMapping("")
    public JSONObject sortBody(@RequestParam("userName") String userName,
                               @RequestParam("fileName") String fileName,
                               @RequestParam("criteria") String criteria,
                               @RequestParam("page") int page) throws IOException, ParseException {
        Sorter sorter = new Sorter();
        return sorter.sort(userName,fileName,criteria, page);
    }
}
