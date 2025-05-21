package com.fiap.hospital.historyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class HistoryApp {

	public static void main(String[] args) {
		SpringApplication.run(HistoryApp.class, args);
	}

}
