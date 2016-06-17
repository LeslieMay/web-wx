package com.swu.wechat.action;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.swu.wechat.Impl.UserDaoImpl;
import com.swu.wechat.dao.UserDao;
import com.swu.wechat.entity.User;



public class LoginAction implements Action{

	private String username;
	private String password;
	private int phonenumber;
	
	
	
	


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String execute() throws Exception {
		boolean flag = false;
			flag = userLogin();
		if(flag) return SUCCESS;
		return ERROR;
	}
	private boolean userLogin(){
		UserDao userdao=new UserDaoImpl();
		User user =userdao.Login(username, password);
		
		if(user != null){
			 Map session = ActionContext.getContext().getSession();
			 session.put("user", user);
			 ActionContext.getContext().setSession(session);
			 System.out.println("放入User");
			 return true;
		}
		else{
			Map session = ActionContext.getContext().getSession();
			 session.put("Islogin","0");
			 ActionContext.getContext().setSession(session);
			 return false;
		}
		
		
	}
	public String out(){
	
		 Map session = ActionContext.getContext().getSession();
		 session.put("user", null);
		 ActionContext.getContext().setSession(session);
		return SUCCESS;
	}

}
