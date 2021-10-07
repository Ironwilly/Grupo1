package com.salesianostriana.dam.P01;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(description = "API para controlar la app Trianafy",
		version = "1.0",
		contact = @Contact(email = "chamorro.gaser21@triana.salesianos.edu", name = "Sergio Chamorro García"),
		license = @License(name = "CreativeCommons 3.0"),
		title = "API Trianafy"
)
)
public class P01Application {

	public static void main(String[] args) {
		SpringApplication.run(P01Application.class, args);
	}

}
