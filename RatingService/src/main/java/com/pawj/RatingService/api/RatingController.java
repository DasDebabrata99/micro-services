package com.pawj.RatingService.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawj.RatingService.domain.Rating;

@RestController
public class RatingController {

	@GetMapping("/rating")
	public Rating getUserRatings() {
		
		Rating rating = new Rating.RatingBuilder("Deb").setRating(5).setIsPromoted(true).build();
		System.out.println("returning the rating response: " + rating);
		return rating;
		
	}
}
