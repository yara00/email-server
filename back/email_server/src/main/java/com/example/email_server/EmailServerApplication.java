package com.example.email_server;
import com.example.email_server.filter.*;
import com.example.email_server.sorting.Sorter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class EmailServerApplication {

	public static void main(String[] args) throws IOException, ParseException {
		SpringApplication.run(EmailServerApplication.class, args);
		//Sorter sorter = new Sorter();
		//System.out.println(sorter.sort("marioma","Sent","sender"));


	}

}
