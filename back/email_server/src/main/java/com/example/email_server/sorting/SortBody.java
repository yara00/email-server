package com.example.email_server.sorting;
import com.example.email_server.facade.Message;

public class SortBody implements ISort{
    @Override
    public int compare(Message a, Message b)
    {
        return a.getMessageBody().getBody().compareTo(b.getMessageBody().getBody());
    }

}
