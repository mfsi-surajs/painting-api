package com.api.painting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication()
//@ComponentScan(basePackages= {"com.api.painting.service","com.api.painting.controller","com.api.painting.repository"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
