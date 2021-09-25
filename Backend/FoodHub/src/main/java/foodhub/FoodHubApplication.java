package foodhub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoodHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodHubApplication.class, args);
	}
	/*
	@Bean
	CommandLineRunner initCustomer(CustomerRepository customerRepository) {
		return args -> {
			Customer c1 = new Customer("John","john@somemail.com","John","John Dr.");
			customerRepository.save(c1);
		};
	}
	*/
}
