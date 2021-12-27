package com.example.email_server.sorting;

import com.example.email_server.facade.Message;


public class SortPriority implements ISort {
    @Override
    public int compare(Message a, Message b)
    {
        return (a.getMessageHeader().getPriority()) - (b.getMessageHeader().getPriority());
    }
}
