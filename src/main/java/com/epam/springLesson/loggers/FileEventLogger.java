package com.epam.springLesson.loggers;

import com.epam.springLesson.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Lazy
@Scope("prototype")
@Component("fileEventLogger")
public class FileEventLogger implements EventLogger {
    String fileName;
    private File file;

    @Autowired
    FileEventLogger(@Value("JustFile.txt") String fileName) {
        this.fileName = fileName;
    }

    public FileEventLogger() {}

    public void logEvent(Event event) {
        try {
//            init();
            FileUtils.writeStringToFile(file, event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() throws IOException {
        this.file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        if (!file.canWrite()) {
            throw new IOException();
        }
    }
}
