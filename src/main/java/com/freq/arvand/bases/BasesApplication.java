package com.freq.arvand.bases;

import com.freq.arvand.bases.user.UserImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BasesApplication {
    public static void main(String[] args) {
        SpringApplication.run(BasesApplication.class, args);

    }
    @Bean
    CommandLineRunner run(UserImp userService) {
        return args -> {
            System.out.println("✅ Spring is UP, running test...");
            userService.test();
        };
    }
}
