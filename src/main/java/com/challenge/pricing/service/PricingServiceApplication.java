package com.challenge.pricing.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class PricingServiceApplication {

@Value("${application.timezone:UTC}")
private String timezone;

	public static void main(String[] args) {
        SpringApplication.run(PricingServiceApplication.class, args);
	}

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));
    }

}
