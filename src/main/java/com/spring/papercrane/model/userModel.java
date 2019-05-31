package com.spring.papercrane.model;

public class userModel {
	// 유저
	private String id; // 아이디
	private String password; // 비밀번호
	
	public userModel() {
	}
	
	public userModel(String id, String password) {
		this.password=password;
		this.id=id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "userModel [id=" + id + ", password=" + password + "]";
	}
	
	

	
}
