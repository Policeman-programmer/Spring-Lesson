package com.epam.springLesson;

import com.epam.springLesson.loggers.EventLogger;
import com.epam.springLesson.loggers.LoggersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("app")
public class App {
    private Client client;
//    @Qualifier("cashFileLogger")
    private String defaultLocale;
    private EventLogger defaultLogger;
    private Map<String, EventLogger> loggers;

    private static AnnotationConfigApplicationContext ctx;

    @Autowired
    public App(Client client, @Value("#{T(com.epam.springLesson.Event).isDay() ? fileEventLogger : consoleEventLogger}")EventLogger eventLogger, Map<String, EventLogger> loggers) {
        this.loggers = loggers;
        this.client = client;
        defaultLogger = eventLogger;
    }

    public static void main(String[] args) {
//        ctx = new ClassPathXmlApplicationContext("spring.xml"); // it is used when there is spring.xml
//        ctx.register(AppConfig.class);
//        ctx.register(LoggersConfig.class); //another way to register bean
//        ctx.refresh();
        ctx = new AnnotationConfigApplicationContext(AppConfig.class, LoggersConfig.class);
        ctx.scan("com.epam.springLesson");
//        ctx.refresh();

        App app = (App) ctx.getBean("app");

        String massage = "Some event for user 1";
        EventType type = EventType.ERROR;

        app.logEvent(massage, type);

        massage = "Another event for user 1";
        type = EventType.INFO;
        app.logEvent(massage, type);

        massage = "one more message event for user 1";
        type = null;
        app.logEvent(massage, type);

        ConfigurableApplicationContext cac = new ClassPathXmlApplicationContext("spring.xml");
        cac.close();
    }

    private void logEvent(String msg, EventType type) {
        msg = msg.replaceAll(client.getId(), client.getFullName() + " says " + client.getGreeting());

        Event event = (Event) App.ctx.getBean("event");
        event.setMsg(msg);

        if (type == null) {
            defaultLogger.logEvent(event);
        }else {
            String typeStr = type.toString();EventLogger logger = loggers.get(typeStr);
            if (logger == null) {
                defaultLogger.logEvent(event);
            } else {
                logger.logEvent(event);
            }
        }

    }

}
