package com.nour.saray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SarayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SarayApplication.class, args);
	}

}
