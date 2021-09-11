package com.example.demo;
import org.springframework.web.bind.annotation.*;

@RestController
public class WelcomePage {

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Spring Experiments";
	}
}
