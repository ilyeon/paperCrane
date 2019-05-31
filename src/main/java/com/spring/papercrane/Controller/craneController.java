package com.spring.papercrane.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.xdevapi.JsonArray;
import com.spring.papercrane.Service.craneService;
import com.spring.papercrane.Service.repleService;
import com.spring.papercrane.model.craneModel;
import com.spring.papercrane.model.repleModel;

@Controller("craneController")
public class craneController {
	@Autowired
	private craneService craneService;

	@Autowired
	private repleService repleService;

	@RequestMapping(value = "/craneTest", method = RequestMethod.GET)
	public @ResponseBody void craneTest() {
		System.out.println("craneTest");
		List<craneModel> list = craneService.craneTest();
		List<repleModel> repleList = repleService.getRepleTest();

		for (craneModel m : list) {
			System.out.println(m.toString());
		}
		
		for(repleModel r:repleList) {
			System.out.println(r.toString());
		}
	}

	@RequestMapping(value = "/randCrane", method = RequestMethod.GET)
	public @ResponseBody JSONObject randCrane(HttpServletRequest request) throws Exception {
		JSONObject jsonMain = new JSONObject();
		JSONObject repleJson=new JSONObject();
		JSONArray repleArray=new JSONArray();
		
		String userid = request.getParameter("id"); // request.getParameter() 더미

		craneModel crane = craneService.randomCrane(userid); //글 가져오기	
		jsonMain.put("CraneText", crane.getCrane());
		
		List<repleModel> repleList=repleService.getReple(crane.getUserid(),crane.getNum()); //글에 맞는 리플 가져오기
		
		for(repleModel r:repleList) {
			repleJson=new JSONObject();
			repleJson.put("CraneUserid", r.getReUserid());
			repleJson.put("CraneNum", r.getReNum());
			repleJson.put("repleText", r.getReple());
			repleJson.put("color", r.getColor());
			repleJson.put("stamp", r.getStamp());
			repleJson.put("x", r.getXf());
			repleJson.put("y", r.getYf());
			repleArray.add(repleJson);
			
		}
		
		jsonMain.put("RepleData", repleArray);
		
		
		return jsonMain;
	}

	
	
	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public @ResponseBody JSONObject myCraneList(HttpServletRequest request) throws Exception {
		JSONObject jsonMain=new JSONObject();
		JSONObject repleJson=new JSONObject();
		JSONArray repleArray=new JSONArray();
		
		
		String userid=request.getParameter("id"); //더미
		
		List<craneModel> list=craneService.myList(userid);
		
		for(craneModel c:list) {
			repleJson=new JSONObject();
			repleJson.put("number", c.getNum());
			repleJson.put("timeStamp", c.getDate());
			repleArray.add(repleJson);
			
		}
		
		jsonMain.put("MyList", repleArray);
		
		return jsonMain;
	}

	
	
	
	@RequestMapping(value = "/myCrane", method = RequestMethod.GET)
	public @ResponseBody JSONObject myCrane(HttpServletRequest request) throws Exception {
		JSONObject jsonMain = new JSONObject();
		JSONObject repleJson=new JSONObject();
		JSONArray repleArray=new JSONArray();
		
		String userid = request.getParameter("id"); // request.getParameter() 더미
		int num=Integer.parseInt(request.getParameter("num")); //더미 
		
		craneModel crane = craneService.myCrane(userid, num);
		jsonMain.put("CraneText", crane.getCrane());
		
		List<repleModel> repleList=repleService.getReple(crane.getUserid(),crane.getNum()); //글에 맞는 리플 가져오기
		
		for(repleModel r:repleList) {
			repleJson=new JSONObject();
			repleJson.put("CraneUserid", r.getReUserid());
			repleJson.put("CraneNum", r.getReNum());
			repleJson.put("repleText", r.getReple());
			repleJson.put("color", r.getColor());
			repleJson.put("stamp", r.getStamp());
			repleJson.put("x", r.getXf());
			repleJson.put("y", r.getYf());
			repleArray.add(repleJson);
			
		}
		
		jsonMain.put("RepleData", repleArray);
		
		
		return jsonMain;
	}
	
	@RequestMapping(value = "/insertMyCrane", method = RequestMethod.GET)
	public @ResponseBody void insertCrane(HttpServletRequest request) throws Exception{
		System.out.println("insert Crane");
		
		String userid=request.getParameter("userid"); //더미
		String craneText=request.getParameter("craneText"); //더미
		
		craneService.insertMyCrane(userid, craneText);
	}
	
	
	@RequestMapping(value = "/insertReple", method = RequestMethod.GET)
	public @ResponseBody void insertReple(HttpServletRequest request) throws Exception {
		System.out.println("insert Reple");
		
		String reUserid=request.getParameter("reUserid"); //더미 선택된 글 본 주인 아이디
		int reNum=Integer.parseInt(request.getParameter("reNum")); //더미 선택된 글 번호
	
		
		String repleText=request.getParameter("repleText"); //더미
		int color=Integer.parseInt(request.getParameter("color")); //더미 
		
		int stamp=Integer.parseInt(request.getParameter("stamp")); //더미
		
		float xf=Float.parseFloat(request.getParameter("xf")), 
				yf=Float.parseFloat(request.getParameter("yf")); //더미
	
		if(!(repleText==null) && color!=0) {
			System.out.println("text");
			repleService.insertRepleText(reUserid, reNum, repleText, color, xf, yf);
		} else if(stamp!=0){
			System.out.println("stamp");
			repleService.insertRepleStamp(reNum, reUserid, stamp, xf, yf);
		}
		
		
	}
	
}
