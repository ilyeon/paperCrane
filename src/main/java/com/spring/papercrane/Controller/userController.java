package com.spring.papercrane.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.papercrane.Service.userService;
import com.spring.papercrane.model.userModel;
import com.spring.papercrane.modelDAO.userDAO;

@Controller("userController")
public class userController {

	@Autowired
	private userService service;

	@RequestMapping(value = "/userGet", method = RequestMethod.GET)
	public @ResponseBody JSONObject userList(HttpServletRequest request) throws Exception {
		JSONObject jsonMain = new JSONObject();
		
			String test=null;
			String test2=null;
			test=request.getParameter("aaa");
			test2=request.getParameter("bbb");
			
			int numtest=0;
			numtest=Integer.parseInt(request.getParameter("num"));
			
			System.out.println(test);
			System.out.println(test2);
			System.out.println(numtest);
		
		return jsonMain;
	}

	@RequestMapping(value = "/userCheck", method = RequestMethod.GET)
	public @ResponseBody JSONObject checkUserIS(HttpServletRequest request) throws Exception {
		JSONObject jsonMain = new JSONObject();

		// 우선 더미
		String id = request.getParameter("id"); // request.getParameter("안드로이드 측 http.addOrReplace(키,값) 의 키");
		String pw = request.getParameter("pw"); // 동일
		String requestType = request.getParameter("requestType"); // login 또는 sign 으로 보내기
	

		
		System.out.println(id);
		System.out.println(pw);
		System.out.println(requestType);
		
		userModel user = service.getUserForLogin(id, pw);

		if (user != null && requestType.equals("login")) {
			System.out.println("login");
			System.out.println(user.toString());
			jsonMain.put("Result", 1);
			// login 성공 = 1
		} else if (user == null && requestType.equals("sign")) {
			System.out.println("sign");
			service.signInsertUser(id, pw);
			jsonMain.put("Result", 2);
			// sign 성공 = 2
		} else if (user == null && requestType.equals("login")) {
			jsonMain.put("Result", 3);
			// login 실패 = 3
		} else if (user != null && requestType.equals("sign")) {
			System.out.println("sign fail");
			jsonMain.put("Result", 4);
			// sign 실패 = 4
		}
		
		return jsonMain;
	}

}
