package ru.makosiale.coffeemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CoffeeMachineApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoffeeMachineApplication.class, args);
    }

}
