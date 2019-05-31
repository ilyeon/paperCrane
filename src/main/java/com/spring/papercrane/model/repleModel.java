package com.spring.papercrane.model;

public class repleModel {

	// 본 글 식별용
	private int reNum; // 글 번호 PK NN FK
	private String reUserid; // 사용자 아이디 PK NN FK

	// 댓글 식별용
	private int repleNum; // 댓글 번호 PK NN

	// 리플
	private String reple = null; // 댓글 내용
	private int color = 0;

	private int stamp = 0; // 스탬프 번호

	// 좌표
	private float xf = 0; // x좌표
	private float yf = 0; // y좌표

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getReNum() {
		return reNum;
	}

	public void setReNum(int reNum) {
		this.reNum = reNum;
	}

	public String getReUserid() {
		return reUserid;
	}

	public void setReUserid(String reUserid) {
		this.reUserid = reUserid;
	}

	public int getRepleNum() {
		return repleNum;
	}

	public void setRepleNum(int repleNum) {
		this.repleNum = repleNum;
	}

	public String getReple() {
		return reple;
	}

	public void setReple(String reple) {
		this.reple = reple;
	}

	public int getStamp() {
		return stamp;
	}

	public void setStamp(int stamp) {
		this.stamp = stamp;
	}

	
	public float getXf() {
		return xf;
	}

	public void setXf(float xf) {
		this.xf = xf;
	}

	public float getYf() {
		return yf;
	}

	public void setYf(float yf) {
		this.yf = yf;
	}

	@Override
	public String toString() {
		return "repleModel [reNum=" + reNum + ", reUserid=" + reUserid + ", repleNum=" + repleNum + ", reple=" + reple
				+ ", color=" + color + ", stamp=" + stamp + ", xf=" + xf + ", yf=" + yf + "]";
	}

}
