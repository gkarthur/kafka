package com.capgemini.per.logistique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.capgemini.per.logistique, com.capgemini.per.evenements.producer, com.capgemini.per.evenements.demandes, com.capgemini.per.evenements.services")
public class Application {	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }      
}