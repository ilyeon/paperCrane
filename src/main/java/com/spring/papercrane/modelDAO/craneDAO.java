package com.spring.papercrane.modelDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.papercrane.model.craneModel;
import com.spring.papercrane.model.userModel;

@Repository("craneDAO")
public class craneDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String craneTestQuery = "select * from craneTBL";
	private static final String selectCraneForReple = "select * from craneTBL where userid not in (?) and repleCount<20";
	private static final String myCraneList_select = "select num, userid, crane, DATE_SUB(paperdate, INTERVAL 9 HOUR) as paperdate , repleCount from craneTBL where userid=?";
	private static final String myCrane_select = "select * from craneTBL where userid=? and num=?";

	private static final String insert_myCrane = "insert into craneTBL(num,userid,crane,paperdate,repleCount) "
			+ "values( (select count(case when userid=? then 0 end) from craneTBL crane)+1,?,?,?,0);";

	private static final String update_repleCount="update craneTBL set repleCount=repleCount+1 where userid=? and num=?";
	
	public List<craneModel> craneTest() {
		System.out.println("craneTest");
		return jdbctemplate.query(craneTestQuery, new craneRowMapper());
	}

	public List<craneModel> getCraneListNOTIN(String userid) {
		System.out.println("getCraneListNOTIN");

		return jdbctemplate.query(selectCraneForReple, new Object[] { userid }, new craneRowMapper());
	}

	public List<craneModel> getMyCraneList(String userid) {
		System.out.println("My crane List");

		return jdbctemplate.query(myCraneList_select, new Object[] { userid }, new craneRowMapper());
	}

	public craneModel getMyCrane(String userid, int num) {
		System.out.println("getMycrane");

		return jdbctemplate.queryForObject(myCrane_select, new Object[] { userid, num }, new craneRowMapper());
	}

	
	public void insertMyCrane(String userid, String craneText, String time) {
		System.out.println("쿼리 실행 전");
		jdbctemplate.update(insert_myCrane, new Object[] {userid, userid,craneText,time});
		System.out.println("쿼리 성공");
	}
	
	
	public void updateRepleCount(String userid, int num) {
		System.out.println("replecount update");
		jdbctemplate.update(update_repleCount,new Object[] {userid,num});
	}
}

class craneRowMapper implements RowMapper<craneModel> {

	@Override
	public craneModel mapRow(ResultSet rs, int rowNum) throws SQLException {

		craneModel model = new craneModel();

		model.setNum(rs.getInt("num"));
		model.setUserid(rs.getString("userid"));
		model.setCrane(rs.getString("crane"));
		model.setPaperdate(rs.getTimestamp("paperdate"));
		model.setRepleCount(rs.getInt("repleCount"));

		return model;
	}

}
