package com.staskost.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.staskost.jdbc.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void createUser(User user) {
		String sql = "INSERT INTO user(first_name, last_name, email, address) VALUES(?,?,?,?)";
		jdbcTemplate.update(sql,
				new Object[] { user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress() });

	}

	@Override
	public User getUserById(int id) {
		String sql = "SELECT * FROM user WHERE id = ?";
		User user = (User) jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setAddress(rs.getString(5));
				return user;
			}
		});
		return user;
	}

	@Override
	public void updateUser(User user) {
		String sql = "UPDATE user SET first_name = ?, last_name = ?, email = ?, address = ? WHERE id=?";
		jdbcTemplate.update(sql, new Object[] { user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getAddress(), user.getId() });

	}

	@Override
	public void deleteUser(int id) {
		String sql = "DELETE FROM user WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] { id });

	}

	@Override
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM user";

		List<User> userList = jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {
			@Override
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<User> list = new ArrayList<User>();
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getInt(1));
					user.setFirstName(rs.getString(2));
					user.setLastName(rs.getString(3));
					user.setEmail(rs.getString(4));
					user.setAddress(rs.getString(5));
					list.add(user);
				}
				return list;
			}

		});
		return userList;
	}

}
