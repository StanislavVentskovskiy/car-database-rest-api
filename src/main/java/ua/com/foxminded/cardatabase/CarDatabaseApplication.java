package ua.com.foxminded.cardatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class CarDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarDatabaseApplication.class, args);
	}
}
