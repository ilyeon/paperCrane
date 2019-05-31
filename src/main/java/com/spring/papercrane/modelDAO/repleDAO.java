package com.spring.papercrane.modelDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.papercrane.model.repleModel;

@Repository("repleDAO")
public class repleDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String repleTest="select * from repleTBL";
	private static final String reple_Select = "select * from repleTBL where reNum=? and reUserid=?";
	private static final String reple_Insert_Text="insert into repleTBL(reNum, reUserid, repleNum, repleText, color, xf,yf) "
			+ "values(?,?, "
			+ "(select count(case when reUserid=? and reNum=? then 0 end) from repleTBL reple)+1 ,"
			+ " ? , ?,?,?)";
	
	private static final String reple_Insert_Stamp="insert into repleTBL(reNum, reUserid, repleNum, stamp, xf,yf)"
			+ "values(?,?, "
			+ "(select count(case when reUserid=? and reNum=? then 0 end) from repleTBL reple)+1 ,"
			+ " ?,?,?)";
	
	
	public List<repleModel> getRepleTest(){
		System.out.println("getRepleTest");
		return jdbcTemplate.query(repleTest, new repleRowMapper());
	}
	
	
	public List<repleModel> getReple(String reUserid, int reNum){
		System.out.println("get Reple");
		return jdbcTemplate.query(reple_Select, new Object[] {reNum,reUserid}, new repleRowMapper());
	}


	
	public void insert_RepleText(int reNum,String reUserid, String repleText, int color, float xf,float yf) {
		System.out.println("text insert");
		jdbcTemplate.update(reple_Insert_Text,new Object[] {reNum,reUserid,reUserid,reNum,repleText,color,xf,yf});
	}
	
	public void insert_RepleStamp(int reNum, String reUserid, int stamp, float xf,float yf) {
		System.out.println("stamp insert");
		jdbcTemplate.update(reple_Insert_Stamp,new Object[] {reNum,reUserid,reUserid,reNum,stamp,xf,yf});
	}

}




class repleRowMapper implements RowMapper<repleModel>{

	@Override
	public repleModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		repleModel reple=new repleModel();
		reple.setReNum(rs.getInt("reNum"));
		reple.setReUserid(rs.getString("reUserid"));
		reple.setRepleNum(rs.getInt("repleNum"));
		reple.setReple(rs.getString("repleText"));
		reple.setColor(rs.getInt("color"));
		reple.setStamp(rs.getInt("stamp"));
		reple.setXf(rs.getFloat("xf"));
		reple.setYf(rs.getFloat("yf"));
		return reple;
	}
	
}
