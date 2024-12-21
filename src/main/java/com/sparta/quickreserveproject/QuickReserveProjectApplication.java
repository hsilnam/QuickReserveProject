package com.sparta.quickreserveproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuickReserveProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickReserveProjectApplication.class, args);
    }

}
