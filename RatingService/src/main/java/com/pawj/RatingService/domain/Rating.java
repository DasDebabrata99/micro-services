package com.pawj.RatingService.domain;

public class Rating {

	public String userName;
	public int rating;
	public transient boolean isPromoted;
	
	private Rating(RatingBuilder builder) {
		this.userName = builder.userName;
		this.rating = builder.rating;
		this.isPromoted = builder.isPromoted;
	}
	
	public static class RatingBuilder{
		
		private String userName;
		private int rating;
		private boolean isPromoted;
		
		public RatingBuilder(String userName) {
			this.userName = userName;
		}
		
		public RatingBuilder setRating(int rating) {
			this.rating = rating;
			return this;
		}
		
		public RatingBuilder setIsPromoted(boolean isPromoted) {
			this.isPromoted = isPromoted;
			return this;
		}
		
		public Rating build() {
			return new Rating(this);
		}
		
	}
	
	
}
