package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortalCoreApplication {

	public PortalCoreApplication() {
	System.out.println("PortalCoreApplication App Created....");
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(PortalCoreApplication.class, args);
	}
}