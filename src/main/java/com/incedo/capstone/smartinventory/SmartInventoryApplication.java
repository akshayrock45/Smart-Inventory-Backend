package com.incedo.capstone.smartinventory;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title = "Smart-Inventory-Management-System",version="2.0",description="SIMS REST API",license=@License(name="License1"),contact=@Contact(name="NorthStars",email="Northstars2023sept@incedoinc.com")))
public class SmartInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartInventoryApplication.class, args);
	}

}
