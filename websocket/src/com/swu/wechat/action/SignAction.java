package com.swu.wechat.action;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.swu.wechat.Impl.UserDaoImpl;
import com.swu.wechat.dao.UserDao;
import com.swu.wechat.entity.User;



public class SignAction implements Action { 
	     private User user;
	   
	     




	public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
	public String execute() throws Exception {
		boolean flag = false;
		UserDao userdao=new UserDaoImpl();
		flag=userdao.addUser(user);
		if(flag){
		return SUCCESS;
		}
		else
		{
			return ERROR;
		}
	}
	public String add() throws Exception{
		return null;

	}

}
