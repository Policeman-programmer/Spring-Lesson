package com.epam.springLesson;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import static org.springframework.beans.factory.config.PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE;

@Configuration
public class AppConfig {

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){
        PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
        placeholderConfigurer.setLocations(new ClassPathResource("client.properties"));
        placeholderConfigurer.setIgnoreResourceNotFound(true);
        placeholderConfigurer.setSystemPropertiesMode(SYSTEM_PROPERTIES_MODE_OVERRIDE);
        return placeholderConfigurer;
    }


    @Value("${name}")
    public String name;

    @Bean
    public Client client(){
        return new Client("1", name);
    }

}
