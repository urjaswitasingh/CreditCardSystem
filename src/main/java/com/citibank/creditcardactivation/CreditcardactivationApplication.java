package com.citibank.creditcardactivation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CreditcardactivationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditcardactivationApplication.class, args);
	}

}
