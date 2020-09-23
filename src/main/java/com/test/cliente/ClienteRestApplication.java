package com.test.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClienteRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteRestApplication.class, args);
	}

}
