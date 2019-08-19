package com.epam.springLesson.loggers;

import com.epam.springLesson.Event;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

public class CashFileLogger extends FileEventLogger {

    private int cashSize;
    private List<Event> cash = new ArrayList<Event>();

    public CashFileLogger(String fileName, int cashSize) {
        super(fileName);
        this.cashSize = cashSize;
    }

    public void logEvent(Event event){
        cash.add(event);

        if(cash.size() == cashSize){
            writeEventsFromCash();
            cash.clear();
        }
    }

    private void writeEventsFromCash() {
        for (Event event : cash) {
            super.logEvent(event);
        }
    }

    @PreDestroy
    public void destroy(){
        if(!cash.isEmpty()){
            writeEventsFromCash();
        }
    }

}
