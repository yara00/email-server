package com.example.email_server.sorting;
import com.example.email_server.facade.Message;
import com.example.email_server.facade.MessageHeader;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class SortDate implements ISort{
    @Override
    public int compare(JSONObject a, JSONObject b)
    {
      //  System.out.println("A IS"+a+" B IS "+b);
        JSONObject aa = (JSONObject) a.get("messageHeader");
        JSONObject bb = (JSONObject) b.get("messageHeader");
        //return aa.get("sender").toString().compareTo(bb.get("sender").toString());

        String strd1 = (String) aa.get("date");
        String strd2 = (String) bb.get("date");

        SimpleDateFormat formatter =new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        Date fdate1= null;
        try {
            fdate1 = formatter.parse(strd1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date fdate2= null;
        try {
            fdate2 = formatter.parse(strd2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fdate2.compareTo(fdate1);
    }

}
