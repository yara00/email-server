package com.example.email_server.sorting;

import com.example.email_server.facade.Message;


public class SortSubject implements ISort {
    @Override
    public int compare(Message a, Message b)
    {
        return a.getMessageHeader().getSubject().compareTo(b.getMessageHeader().getSubject());
    }
}
