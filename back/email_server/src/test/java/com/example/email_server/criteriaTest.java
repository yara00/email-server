package com.example.email_server;

import com.example.email_server.filter.CriteriaSender;
import com.example.email_server.filter.CriteriaSubject;
import com.example.email_server.filter.ICriteria;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

public class criteriaTest {
    @Test
    public void criteriaSender() throws IOException, ParseException {
       // JSONObject mailHeader,  "ana");
        ICriteria senderCriteria = new CriteriaSender();
        JSONParser parser = new JSONParser();
        String filePath = "C:\\Users\\Dell\\Desktop\\users\\marioma\\Inbox.json";
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
        JSONArray msg = (JSONArray) jsonObject.get("Inbox");

        JSONArray test = senderCriteria.meetCriteria(msg,"lolo");

        Assert.assertEquals(1, test.size());
    }

    @Test
    public void criteriaSubject() throws IOException, ParseException {
        // JSONObject mailHeader,  "ana");
        ICriteria subjectCriteria = new CriteriaSubject();
        JSONParser parser = new JSONParser();
        String filePath = "C:\\Users\\Dell\\Desktop\\users\\marioma\\Sent.json";
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
        JSONArray msg = (JSONArray) jsonObject.get("Sent");
        JSONArray test = subjectCriteria.meetCriteria(msg,"Hello");

        Assert.assertEquals(2, test.size());
    }
}
