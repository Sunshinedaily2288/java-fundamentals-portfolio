package com.matharsa.web_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);

        // This forces database settings directly into the Java engine, bypassing file paths entirely
        Map<String, Object> properties = new HashMap<>();
        properties.put("spring.datasource.url", "jdbc:h2:file:./data/blogdb;DB_CLOSE_DELAY=-1");
        properties.put("spring.datasource.driverClassName", "org.h2.Driver");
        properties.put("spring.datasource.username", "sa");
        properties.put("spring.datasource.password", "");
        properties.put("spring.jpa.hibernate.ddl-auto", "update");
        properties.put("spring.jpa.show-sql", "true");

        app.setDefaultProperties(properties);
        app.run(args);
    }
}
