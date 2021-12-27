package com.example.email_server.sorting;

import com.example.email_server.facade.Message;
import java.util.Comparator;

public interface ISort extends Comparator<Message> {
    int compare(Message a, Message b);
}
