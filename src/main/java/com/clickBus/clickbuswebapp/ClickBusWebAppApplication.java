package com.clickBus.clickbuswebapp;

import com.clickBus.clickbuswebapp.Repository.BusRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class ClickBusWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClickBusWebAppApplication.class, args);
	}

}
