package com.spring.papercrane;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class dbtest {
	@Test
	public void test() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con =     DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/papercrane?serverTimezone=UTC","cr","1111"); 
	    System.out.println(con);
	}
}
