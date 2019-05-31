package com.spring.papercrane.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.papercrane.model.repleModel;
import com.spring.papercrane.modelDAO.repleDAO;

@Service("repleService")
public class repleService {
	@Autowired
	private repleDAO dao;
	
	public List<repleModel> getRepleTest(){
		return dao.getRepleTest();
	}
	
	public List<repleModel> getReple(String reUserid,int reNum){
		System.out.println("service ["+reUserid+" / "+reNum+"]");
		return dao.getReple(reUserid, reNum);
	}

	public void insertRepleText(String reUserid, int reNum, String repleText, int color, float xf,float yf) {
		System.out.println("text");
		dao.insert_RepleText(reNum, reUserid, repleText, color, xf, yf);
	}
	
	public void insertRepleStamp(int reNum,String reUserid, int stamp, float xf,float yf) {
		System.out.println("stamp");
		dao.insert_RepleStamp(reNum, reUserid, stamp, xf, yf);
	}

}
