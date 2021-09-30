package foodhub;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.boot.security.*;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

@RestController
public class Controller {
	
	@Autowired
	AdminRepository adminRepository;

	@Autowired
	CustomerRepository customerRepository;
	
	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
    
    @GetMapping(path = "/admins")
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }
    
    // Obviously, this is a massive security problem.
    @PostMapping(path = "/admins")
    public String createAdmin(@RequestBody Admin admin) {
    	if (admin == null)
    		return failure;
    	List<Admin> sameEmail = adminRepository.findByEmail(admin.getEmail());
    	if (sameEmail != null  && 0 < sameEmail.size())
    		return failure;
    	adminRepository.save(admin);
    	return success;
    }

	@GetMapping("notlogged")
	public String notLogged() {
		return "You are not logged in";
	}
	
	/*
    @GetMapping(path = "/customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }*/
    
    @GetMapping("/customers")
    public String listCustomers(Model model) {
    	List<Customer> listCustomers = customerRepository.findAll();
    	model.addAttribute("list customers", listCustomers);
    	return "customers";
    }

    @PostMapping(path = "/customers")
    public String createCustomer(@RequestBody Customer customer) {
    	if (customer == null)
    		return failure;
    	List<Customer> sameEmail = customerRepository.findByEmail(customer.getEmail());
    	if (sameEmail != null  && 0 < sameEmail.size())
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
