package com.spring.papercrane.modelDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.papercrane.model.userModel;

@Repository("userDAO")
public class userDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String test_Select = "select * from userTBL";
	private static final String getUser_Select = "select * from userTBL where id=? AND password=?";
	private static final String sign_Insert = "insert into userTBL(id, password) values(?,?)";

	public List<userModel> getTestUser() {
		System.out.println("getTestUser");
		return jdbcTemplate.query(test_Select, new userRowMapper());
	}

	public userModel getUserforLogin(String id, String pw) {
		System.out.println("getUserforLogin");

		userModel m = null;

		try {
			m = jdbcTemplate.queryForObject(getUser_Select, new Object[] { id, pw }, new userRowMapper());
		} catch (EmptyResultDataAccessException e) {
			System.out.println("query fail");
			return null;
		}

		return m;
	}

	public void signInsertUser(String id, String pw) {
		System.out.println("signInsertUser");
		jdbcTemplate.update(sign_Insert, new Object[] { id, pw });
	}

}

class userRowMapper implements RowMapper<userModel> {

	@Override
	public userModel mapRow(ResultSet rs, int rowNum) throws SQLException {

		userModel user = new userModel();
		user.setId(rs.getString("id"));
		user.setPassword(rs.getString("password"));
		return user;
	}

}
