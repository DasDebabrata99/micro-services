package com.example.SecurityDemo.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class SecurityController{

	@GetMapping("hello")
	public  String greet(){
		return "Hello There!";
	}
	
	@GetMapping("admin")
	public  String greetAdmin(){
		return "Hello Administrator!";
	}
}
