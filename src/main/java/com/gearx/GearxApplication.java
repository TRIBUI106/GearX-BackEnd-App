package com.gearx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GearxApplication {

    public static void main(String[] args) {

        // Anti failed JDBC Connection
        System.setProperty("user.timezone", "Asia/Ho_Chi_Minh");

        SpringApplication.run(GearxApplication.class, args);
    }
}
