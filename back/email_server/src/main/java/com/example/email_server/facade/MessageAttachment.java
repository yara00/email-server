package com.example.email_server.facade;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import java.util.ArrayList;
import java.util.List;

@JsonSerializableSchema
public class MessageAttachment {
    //instances will contain message attachments
    //that can be attached to a MessageBody object
    List<String> attachmentName;

    public MessageAttachment(List<String> attachmentName) {
        this.attachmentName = attachmentName;
    }

    public List<String> getAttachmentName() {
        return attachmentName;

    }

    public void setAttachmentName(List<String> attachmentName) {
        this.attachmentName = attachmentName;
    }
}
