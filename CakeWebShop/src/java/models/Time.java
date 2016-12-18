package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    
    public String getTimeNow() {
    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentTime = sdf.format(dt);
    return currentTime;
    }
    
    public String getTime(String date) throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date parsed = format.parse(date);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentTime = sdf.format(parsed);
    return currentTime;
    }
}
/*
<%@page import="models.Time"%>
<% Time time = new Time();%>
<%= time.getTimeNow() %>
*/