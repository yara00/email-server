package com.example.email_server.controllers;

import com.example.email_server.sorting.Sorter;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sort")
public class SortController {
    @GetMapping("/criteria")
    public JSONArray sortBody(@RequestParam("username") String userName,
                              @RequestParam("filename") String fileName,
                              @RequestParam("criteria") String criteria) throws IOException, ParseException {
        Sorter sorter = new Sorter();
        return sorter.sort(userName,fileName,criteria);
    }
}
