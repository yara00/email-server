package com.example.email_server;
import com.example.email_server.filter.*;
//import com.example.email_server.sorting.Sorter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication
public class EmailServerApplication {

	public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
		SpringApplication.run(EmailServerApplication.class, args);

		JSONParser parser = new JSONParser();
		String filePath = "C:\\Users\\Dell\\Desktop\\users\\marioma\\Trash.json";
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
		JSONArray msg = (JSONArray) jsonObject.get("Trash");
		Date currDate = new Date();
		JSONArray keep = new JSONArray();
		for (Object o : msg) {
			JSONObject obj = (JSONObject) o;
			JSONObject aa = (JSONObject) obj.get("messageHeader");
			String strd1 = (String) aa.get("date");
			SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
			Date fdate1 = formatter.parse(strd1);

			long diff = currDate.getTime() - fdate1.getTime();
			diff = diff / (1000 * 60 * 60 * 24);
			if(diff < 30) {
				keep.add(o);
			}
		}
		JSONObject obj = new JSONObject();
		obj.put("Trash", keep);
		FileWriter file = new FileWriter(filePath);
		file.write(obj.toJSONString());
		file.flush();
	}
			//return aa.get("sender").toString().compareTo(bb.get("sender").toString());
		//	System.out.println("Difference between  " + "curr" + " and " + "fdate1" + "is "
		//			+ ( + " days.");
			//String strd1 = (String) aa.get("date");



		//	Sorter sorter = new Sorter();
		//	sorter.sort("marioma","trash","date", 0);
	}



