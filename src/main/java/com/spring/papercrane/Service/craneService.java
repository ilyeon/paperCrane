package com.spring.papercrane.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.papercrane.model.craneModel;
import com.spring.papercrane.modelDAO.craneDAO;

@Service("craneService")
public class craneService {
	@Autowired
	private craneDAO dao;
	
	public List<craneModel> craneTest(){
		return dao.craneTest();
	}
	
	public craneModel randomCrane(String userid) {
		
		List<craneModel> list=dao.getCraneListNOTIN(userid);
		
		int size=list.size();
		
		Random rand=new Random();
		
		craneModel model = list.get(rand.nextInt(size));
	
		
		return model;
	}
	
	public List<craneModel> myList(String userid)
	{
		List<craneModel> list=dao.getMyCraneList(userid);
		
		return list;
	}
	
	
	
	public craneModel myCrane(String userid, int num) {
		return dao.getMyCrane(userid, num);
	}
	
	
	
	public void insertMyCrane(String userid, String craneText) {
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String nowTime = null;
		nowTime = formatter.format(cal.getTime());

	
		
		System.out.println("dao 호출 전");
		System.out.println(nowTime);
		System.out.println(userid);
		System.out.println(craneText);
		
		dao.insertMyCrane(userid, craneText, nowTime);
	}
	
	
	public void updateRepleCount(String userid, int num) {
		dao.updateRepleCount(userid, num);
	}
	
}
