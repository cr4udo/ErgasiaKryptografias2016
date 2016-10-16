package com.test;

public class User {
	
		private final String username;

		private final String password;

		private final String firstName;

		private final String lastName;

		public User(String username, String password, String firstName, String lastName) {
			if(username == null) {
				throw new IllegalArgumentException("username cannot be null");
			}
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public String getPassword() {
			return password;
		}

		public String getUsername() {
			return username;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}
	}

