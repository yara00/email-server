package com.example.email_server.facade;

public class MessageAttachment {
    //instances will contain message attachments
    //that can be attached to a MessageBody object
    String attachmentName;

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}
