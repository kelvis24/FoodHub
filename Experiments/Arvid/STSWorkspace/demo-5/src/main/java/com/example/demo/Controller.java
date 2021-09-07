package com.example.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	String str;
	
	public Controller() {
		str = "";
	}
	
	@GetMapping("/getText")
	public String getText() {
		return str;
	}
	
	@PostMapping("/postText")
	public String postText(@RequestParam(value = "text", defaultValue = "") String message) {
		str = str + message + "\n";
		return str;
	}
	
}
