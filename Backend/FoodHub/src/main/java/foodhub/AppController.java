package foodhub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	@Autowired
	private CustomerRepository customerRepo;
	
	@GetMapping("")
	public String getHomePage() {
		return "Index";
	}
}
