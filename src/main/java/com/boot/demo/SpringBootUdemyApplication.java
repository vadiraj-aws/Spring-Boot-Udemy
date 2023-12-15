package com.boot.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;

import static com.boot.demo.logger.Logger.LOG;

@SpringBootApplication
public class SpringBootUdemyApplication {
	
	@Autowired
    private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUdemyApplication.class, args);
	}
	
	@PostConstruct
    public void printProperties() {
		LOG.debug("spring.application.name {} ", environment.getProperty("spring.application.name"));
    }

}
