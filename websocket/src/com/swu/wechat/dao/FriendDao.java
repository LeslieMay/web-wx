package com.swu.wechat.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.opensymphony.xwork2.ActionContext;
import com.swu.wechat.entity.Friend;
import com.swu.wechat.entity.User;


public interface FriendDao {
	public List<User> list(String username);
		
	 public boolean addApplicant(Friend friend);
			
	 public boolean addApplicant2(Friend friend);
		
	 public boolean delUser(Friend friend);
	
			
}
