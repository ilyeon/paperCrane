package com.spring.papercrane.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class craneModel {
	// 글 조회용 (단일. 글조회.)
	// 학 잡기 -> 리플개수 및 리플가능여부 전송하기

	// 리플 불러오기용
	private int num; // 글 번호 PK NN
	private String userid; // 사용자 아이디 PK NN

	// 글 내용
	private String crane; // 글 내용 NN

	// 학 잡기 판별용
	private int repleCount; //리플개수

	// 표시할 내용
	private Timestamp paperdate; // 날짜 시간
	
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCrane() {
		return crane;
	}

	public void setCrane(String crane) {
		this.crane = crane;
	}

	public int getRepleCount() {
		return repleCount;
	}

	public void setRepleCount(int repleCount) {
		this.repleCount = repleCount;
	}

	
	public String getDate() {
		SimpleDateFormat sdfCurrent = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
		String today = sdfCurrent.format(this.paperdate); 
		return today;
	}
	
	public Timestamp getPaperdate() {
		return paperdate;
	}

	public void setPaperdate(Timestamp paperdate) {
		this.paperdate = paperdate;
	}

	@Override
	public String toString() {
		return "craneModel [num=" + num + ", userid=" + userid + ", crane=" + crane + ", repleCount=" + repleCount
				+ ", paperdate=" + paperdate + "]";
	}

	
	

	
}
