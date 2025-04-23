package com.pawj.domain;

public class User {

	public String name;
	public int age;
	public String gender;
	public Rating rating;
	
	private User(UserBuilder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.gender = builder.gender;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
	public static class UserBuilder {
		public String name;
		public int age;
		public String gender;
		
		public UserBuilder(String name) {
			this.name =name;
		}
		
		public UserBuilder setAge (int age ) {
			this.age=age;
			return this;
		}
		
		public UserBuilder setGender(String gender) {
			this.gender = gender;
			return this;
		}
		
		public User build() {
			return new User(this);
		}
	}
}
