package com.example.email_server.facade;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
@JsonSerializableSchema
public class MessageBody {
    String body;
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
