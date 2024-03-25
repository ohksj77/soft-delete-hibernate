package com.soft.deletion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DeletionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeletionApplication.class, args);
    }
}
