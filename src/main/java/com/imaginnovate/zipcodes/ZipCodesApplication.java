package com.imaginnovate.zipcodes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.imaginnovate.zipcodes")
public class ZipCodesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ZipCodesApplication.class, args);
	}

}

