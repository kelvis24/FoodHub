package foodhub;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Runs the backend application.
 */
@SpringBootApplication
@ComponentScan(basePackages = "foodhub")
public class FoodHubApplication {

	/**
	 * Main method; it starts the server
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(FoodHubApplication.class, args);
	}
	
}
