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

//@Controller("userController")
public class userController_dum {

	@Autowired
	private userService service;

	@RequestMapping(value = "/userGet", method = RequestMethod.GET)
	public @ResponseBody JSONObject userList(HttpServletRequest request) throws Exception {
		List<userModel> list;
		list = service.getTestList();

		JSONArray jArray = new JSONArray();
		JSONObject jsonMain = new JSONObject();

		for (userModel u : list) {
			System.out.println(u.toString());
			JSONObject row = new JSONObject();

			// json.put("변수명","값")
			row.put("id", u.getId());
			row.put("password", u.getPassword());

			// 배열에추가. json.add(객체)
			jArray.add(row);
		}

		jsonMain.put("userList", jArray);

		return jsonMain;
	}

	@RequestMapping(value = "/userCheck", method = RequestMethod.GET)
	public @ResponseBody JSONObject checkUserIS(HttpServletRequest request) throws Exception {
		JSONObject jsonMain = new JSONObject();

		// 우선 더미
		String id = "test123"; // request.getParameter("안드로이드 측 http.addOrReplace(키,값) 의 키");
		String pw = "0000"; // 동일
		String requestType = "login"; // login 또는 sign 으로 보내기

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
