package com.swu.wechat.Impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.swu.wechat.tool.DbHelp;
import com.swu.wechat.dao.UserDao;
import com.swu.wechat.entity.User;

public class UserDaoImpl implements UserDao {

	public User Login(String username, String password) {
			User user = null;
			String sql = "select * from user where username = ? and password = ?";
			Object[] arr = {username,password};
			CachedRowSet rs =DbHelp.executeQuery(sql, arr);
			try {
				while (rs.next()){
					user = new User();
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setPhonenumber(rs.getString(4));
					user.setName(rs.getString(5));
				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return user;
		}
	


	public boolean addUser(User user) {
		boolean flag = false;
		String sql = "insert into user(username,password,phonenumber,name) values(?,?,?,?)";
		Object[] arr = {user.getUsername(),user.getPassword(),user.getPhonenumber(),user.getName()};
		if(DbHelp.executeUpdata(sql, arr)>0){
			flag = true;
		}
		return flag;
	}

	public boolean changePassword(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<User> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
