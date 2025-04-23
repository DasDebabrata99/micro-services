package com.example.SpringSecurityJdbc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	
	@GetMapping("")
	public String guest() {
		return "Welcome Guest";
	}
	
	@GetMapping("/user")
	public String user() {
		return "<h1>Welcome User </h1>";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "Welcome Admin";
	}
}
