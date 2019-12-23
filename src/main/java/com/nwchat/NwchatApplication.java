package com.nwchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class NwchatApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(NwchatApplication.class, args);
	}

}
