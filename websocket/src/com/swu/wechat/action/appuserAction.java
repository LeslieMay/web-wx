package com.swu.wechat.action;

import com.opensymphony.xwork2.Action;
import com.swu.wechat.Impl.ApplicantDaoImpl;
import com.swu.wechat.Impl.FriendDaoImpl;
import com.swu.wechat.dao.ApplicantDao;
import com.swu.wechat.dao.FriendDao;
import com.swu.wechat.entity.Friend;
import com.swu.wechat.entity.User;

public class appuserAction implements Action{
	private Friend friend;
	private String username;
	
	

	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String execute() throws Exception {
		return null;
	}
	public String Add() throws Exception{
		FriendDao FD =new FriendDaoImpl();
		if(FD.addApplicant(friend) &&FD.addApplicant2(friend)  ){

			if(new ApplicantDaoImpl().delUser(username)){
				return SUCCESS;
			}
		}
		return null;
	}
	public String Del() throws Exception{
		
		if(new ApplicantDaoImpl().delUser(username)){
			return SUCCESS;
		}
		return null;
	}

}
