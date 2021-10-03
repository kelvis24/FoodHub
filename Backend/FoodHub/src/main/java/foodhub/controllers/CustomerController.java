package foodhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import foodhub.database.*;

import org.springframework.ui.Model;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	
	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";

	@GetMapping("notlogged")
	public String notLogged() {
		return "You are not logged in";
	}
    
    @GetMapping("/customers")
    public List<Customer> listCustomers(Model model) {
    	return customerRepository.findAll();
    }

    @PostMapping(path = "/customers")
    public String createCustomer(@RequestBody Customer customer) {
    	System.out.println("Received");
    	if (customer == null)
    		return failure;
    	Customer sameEmail = customerRepository.findByUsername(customer.getUsername());
    	if (sameEmail != null)
    		return failure;
    	customerRepository.save(customer);
    	return success;
    }
    
    @GetMapping("/signup")
    public String showRegistration(Model model) {
    	model.addAttribute("Customer", new Customer());
    	return "sign-up forum";
    }

    /*
    @PostMapping("/process_register")
    public String processRegister(Customer user) {
    	//Encodes password in database for extra security
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	String ePassword = passwordEncoder.encode(user.getPassword());
    	user.setPassword(ePassword);
    	
    	customerRepository.save(user);
    	
    	return "registration successful";
    }
    */

}
