package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Time {

    public String getTimeNow() {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        return currentTime;
    }
    
     public java.sql.Date getSqlDateNow(String timeStr) throws ParseException {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.sql.Date timeNow = null;
        timeNow = new java.sql.Date(formatter.parse(timeStr).getTime());

        return timeNow;
    }


        public String getTime(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsed = format.parse(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(parsed);
        return currentTime;
    }

    public Date getDateFromString(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Time.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parsed;
    }
}
/*
<%@page import="models.Time"%>
<% Time time = new Time();%>
<%= "Tid lige nu: "+time.getTimeNow()+" - bestemt tid: " %>
<%= time.getTime("2016-12-22") %>
 */
