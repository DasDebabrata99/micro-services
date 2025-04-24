package com.pawj.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pawj.domain.Rating;
import com.pawj.domain.User;
import com.pawj.domain.User.UserBuilder;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class UserController {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/users")
	@CircuitBreaker(name="ratingBreaker", fallbackMethod = "ratingFallback")
	public ResponseEntity<User> getUsers() {
		User user = new User.UserBuilder("Deba").setAge(34).setGender("male").build();
		user.setRating(getRating());
		return  ResponseEntity.ok(user);
	}
	public ResponseEntity<User> ratingFallback(Exception ex){
		System.out.println(ex.getMessage());
		System.out.println("Fallback called.. loading dummy data");
		User user = new User.UserBuilder("dummy_name").setAge(0).setGender("dummy").build();		
		return ResponseEntity.ok(user);
	}
	public Rating getRating() {		
		//String url = "http://localhost:8084/rating";
		String url = "http://RATING-SERVICE/rating";
		ResponseEntity<Rating> rs =  restTemplate.getForEntity(url,Rating.class);		
		Rating rating =  rs.getBody();		
		return rating;		
	}
}
