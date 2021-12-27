package com.example.email_server.sorting;

import com.example.email_server.facade.Message;


public class SortSender implements ISort {
    @Override
    public int compare(Message a, Message b)
    {
        return a.getMessageHeader().getSender().compareTo(b.getMessageHeader().getSender());
    }
}
