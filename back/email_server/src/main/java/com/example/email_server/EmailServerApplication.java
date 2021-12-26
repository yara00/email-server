package com.example.email_server;
import com.example.email_server.filter.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EmailServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServerApplication.class, args);
		JSONArray jsonArray = new JSONArray();
		JSONObject message = new JSONObject();
		IMailOperations x = new MailOperations();
		message.put("lol", "loklhio");

		JSONObject obj = new JSONObject();
		obj.put("userName", "jojo");
		obj.put("password", "koko");
		JSONObject hettt = new JSONObject();
		hettt.put("messageHeader", obj);
		jsonArray.add(hettt);
		ICriteria filter = new CriteriaSender();
		System.out.println(obj);


	}

}
