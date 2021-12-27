package com.example.email_server.sorting;
import com.example.email_server.facade.Message;

public class SortDate implements ISort {
    @Override
    public int compare(Message a, Message b)
    {
        return a.getMessageHeader().getDate().compareTo(b.getMessageHeader().getDate());
    }

}
