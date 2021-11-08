package foodhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Runs the backend application.
 */
@SpringBootApplication
@ComponentScan(basePackages = "foodhub")
public class FoodHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodHubApplication.class, args);
	}
	
}
