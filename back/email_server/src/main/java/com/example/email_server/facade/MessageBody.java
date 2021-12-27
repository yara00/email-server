package com.example.email_server.facade;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
@JsonSerializableSchema
public class MessageBody {
    String body;

    public MessageBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
