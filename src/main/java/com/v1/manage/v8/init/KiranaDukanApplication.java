package com.v1.manage.v8.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KiranaDukanApplication {

	public static void main(String[] args) {
		SpringApplication.run(KiranaDukanApplication.class, args);
	}

}
