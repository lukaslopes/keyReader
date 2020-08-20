package com.lukaslopes.keyreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KeyreaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeyreaderApplication.class, args);
	}

}
