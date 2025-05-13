package com.pawj.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.pawj.domain.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	
	@GetMapping("/rating")
	public Rating getRating();

}
