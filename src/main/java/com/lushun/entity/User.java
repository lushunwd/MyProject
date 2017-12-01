package com.lushun.entity;

import javax.validation.constraints.Size;

public class User {

	private int userId;
	@Size(min = 1, max = 10)
	private String userName;
	private String description;

	public User() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
