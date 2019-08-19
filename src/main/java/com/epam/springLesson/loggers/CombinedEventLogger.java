package com.epam.springLesson.loggers;

import com.epam.springLesson.Event;

import java.util.ArrayList;

public class CombinedEventLogger implements EventLogger {
    ArrayList<EventLogger> eventLoggers;

    public CombinedEventLogger(ArrayList<EventLogger> eventLoggers) {
        this.eventLoggers = eventLoggers;
    }

    public void logEvent(Event event) {
        for (EventLogger logger : eventLoggers) {
            logger.logEvent(event);
        }
    }
}
