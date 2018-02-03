package de.blom.microservices.snooker.breakservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BreakServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BreakServiceApplication.class, args);
    }
}
