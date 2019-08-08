package com.capgemini.per.traces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.capgemini.per.traces, com.capgemini.per.evenements.traces")
public class Application {	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }      
}