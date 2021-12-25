package com.example.email_server;
import com.example.email_server.facade.Message;
import com.example.email_server.facade.MessageAttachment;
import com.example.email_server.facade.MessageBody;
import com.example.email_server.facade.MessageHeader;

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

		Message newMessage = new Message();
		MessageHeader newHeader = new MessageHeader();
		MessageBody newBody = new MessageBody();
		MessageAttachment newAttachment = new MessageAttachment();
		List<String> receivers = new ArrayList<>();
		receivers.add("yaryora@gmail.com");
		receivers.add("marioma@gmail.com");
		newHeader.setPriority(2);
		newHeader.setReceivers(receivers);
		newHeader.setSender("lala@gmail.com");
		newHeader.setSubject("Email Subject");
		newHeader.setDate("25/12/2021");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
		System.out.println(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));

		newHeader.setDate(cal);
		newBody.setBody("Hello testing");
		newMessage.setMessageBody(newBody);
		//newMessage.setMessageAttachment(newAttachment);
		newMessage.setMessageHeader(newHeader);
		ObjectMapper org = new ObjectMapper();
		System.out.println(org.writerWithDefaultPrettyPrinter().writeValueAsString(newMessage));
	}

}
