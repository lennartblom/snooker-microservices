package de.blom.microservices.snooker.gameinformationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GameInformationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameInformationServiceApplication.class, args);
    }
}
