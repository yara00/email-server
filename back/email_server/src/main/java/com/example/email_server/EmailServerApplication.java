package com.example.email_server;
import com.example.email_server.facade.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootApplication
public class EmailServerApplication {

	public static void main(String[] args) throws JSONException, JsonProcessingException {
		SpringApplication.run(EmailServerApplication.class, args);

		MessageCreator messageCreator = new MessageCreator();
		messageCreator.createMessage();
	}


}
