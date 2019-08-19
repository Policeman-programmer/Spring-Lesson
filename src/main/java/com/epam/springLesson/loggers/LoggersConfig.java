package com.epam.springLesson.loggers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;

@Configuration
public class LoggersConfig {

    @Bean
    @Lazy
    @Scope("prototype")
    public ConsoleEventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean
    public CashFileLogger cashFileLogger() {
        return new CashFileLogger("justFile.txt", 2);
    }

    //    <bean id="combinedEventLogger" class="com.epam.springLesson.loggers.CombinedEventLogger">-->
//<!--        <constructor-arg>-->
//<!--            <list>-->
//<!--                <ref bean="consoleEventLogger"/>-->
//<!--                <ref bean="fileEventLogger"/>-->
//<!--            </list>-->
//<!--        </constructor-arg>-->
//<!--    </bean>-->
    @Bean
    public CombinedEventLogger combinedEventLogger() {
        ArrayList<EventLogger> loggers = new ArrayList<EventLogger>();
        loggers.add(new ConsoleEventLogger());
        loggers.add(new FileEventLogger());
        return new CombinedEventLogger(loggers);
    }
}
