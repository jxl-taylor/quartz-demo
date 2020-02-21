package com.quartz.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.quartz.demo.config.DBConnention;
import com.quartz.demo.model.User;

public class UserOperation {
	private static Logger logger=Logger.getLogger(UserOperation.class);
	private DBConnention derbyConnection= new DBConnention();
	
	public User addUser(User user) {
		try {
			String insertQuery="insert into users (userName,age) values ('"+user.getName()+"','"+user.getAge()+"')";
			derbyConnection.executeQuery(insertQuery);
		}catch(Exception e) {
			logger.error(e.toString());
		}
		return user;
	}
	
	public void usersViewer() {
		List<User>users=retrieveUser();
		for(User user:users) {
			System.out.println(user.toString());
		}
	}
	private  List<User> retrieveUser() {
		List<User>users=null;
		try {
			String selectQuery="select* from users";
			users= new ArrayList<>();
			ResultSet rs= derbyConnection.retrieveResult(selectQuery);
			while(rs.next()) {
				User user= new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("userName"));
				user.setAge(rs.getString("age"));
				users.add(user);
			}
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return users;
	}
	
	
}
 