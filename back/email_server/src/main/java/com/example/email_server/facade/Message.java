package com.example.email_server.facade;

public class Message {
    //instances will tie together a MessageHeader
    //object and a MessageBody object
    MessageHeader messageHeader;
    MessageBody messageBody;
    MessageAttachment messageAttachment;

    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    public MessageBody getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(MessageBody messageBody) {
        this.messageBody = messageBody;
    }

    public MessageAttachment getMessageAttachment() {
        return messageAttachment;
    }

    public void setMessageAttachment(MessageAttachment messageAttachment) {
        this.messageAttachment = messageAttachment;
    }
}
