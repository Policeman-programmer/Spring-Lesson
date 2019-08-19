package com.epam.springLesson.loggers;


import com.epam.springLesson.Event;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
