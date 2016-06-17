package com.swu.wechat.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.swu.wechat.dao.FriendDao;
import com.swu.wechat.entity.Friend;
import com.swu.wechat.entity.Message;
import com.swu.wechat.entity.User;
import com.swu.wechat.tool.DbHelp;


public class FriendDaoImpl implements FriendDao{
	public List<User> list(String username){
		List<User> userList = new ArrayList<User>();
		User user = null;
		String sql = "select * from friend where username = ?";
		Object[] arr = {username};
		CachedRowSet rs = DbHelp.executeQuery(sql, arr);
		try {
			while (rs.next()){
			String sql2 = "select * from user where username = ?";
			Object[] arr2 = {rs.getString(3)};
			CachedRowSet rs2 = DbHelp.executeQuery(sql2, arr2);
			try {
				while (rs2.next()){
					user = new User();
					user.setUsername(rs2.getString(2));
					user.setPassword(rs2.getString(3));
					user.setPhonenumber(rs2.getString(4));
					user.setName(rs2.getString(5));
					userList.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	@Test
	public void test(){
		Friend friend=new Friend();
		friend.setUsername("fsafasf");
		friend.setFriendname("safasfa");
		addApplicant(friend);
	}
	 public boolean addApplicant(Friend friend){
			boolean flag = false;
			String sql = "insert into friend(username,friendname) values(?,?)";
			Object[] arr = {friend.getUsername(),friend.getFriendname()};
			if(DbHelp.executeUpdata(sql, arr)>0){
				flag = true;
			}
			return flag;
		}
	 public boolean addApplicant2(Friend friend){
			boolean flag = false;
			String sql = "insert into friend(username,friendname) values(?,?)";
			Object[] arr = {friend.getFriendname(),friend.getUsername()};
			if(DbHelp.executeUpdata(sql, arr)>0){
				flag = true;
			}
			return flag;
		}
	 public boolean delUser(Friend friend){
	
			
			boolean flag = false;
			String sql = "delete from friend where friendname= ?";
			Object[] arr = {friend.getFriendname()};
			if(DbHelp.executeUpdata(sql, arr)>0){
				flag = true;
			}
			return flag;
		}

}
