package com.spring.papercrane.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.papercrane.model.userModel;
import com.spring.papercrane.modelDAO.userDAO;

@Service("userService")
public class userService {

	@Autowired
	private userDAO dao;
	
	public List<userModel> getTestList(){
		return dao.getTestUser();
	}
	
	public userModel getUserForLogin(String id,String pw) {
		return dao.getUserforLogin(id,pw);
	}
	
	public void signInsertUser(String id,String pw) {
		dao.signInsertUser(id, pw);
	}
}
