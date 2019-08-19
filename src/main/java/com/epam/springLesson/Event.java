package com.epam.springLesson;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

@Component("event")
public class Event {
    private int id;
    private String msg;
    private Date date;
    private DateFormat df;

    public static boolean isDay(){
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        return hours > 8 && hours < 17;
    }

    public Event() {
        this.id = (int)(Math.random() * 50 + 1);
        this.date = new Date();
        this.df = DateFormat.getDateTimeInstance();
    }


    @Override
    public String toString() {
        return "com.epam.springLesson.Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

}
