package com.sundaegukbap.banchango;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class BanchangoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanchangoApplication.class, args);
	}

}
