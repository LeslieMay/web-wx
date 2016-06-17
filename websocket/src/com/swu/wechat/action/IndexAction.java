package com.swu.wechat.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.swu.wechat.Impl.ApplicantDaoImpl;
import com.swu.wechat.Impl.FriendDaoImpl;
import com.swu.wechat.entity.Applicant;
import com.swu.wechat.entity.User;
import com.websocket.chat.ChatAnnotation;


public class IndexAction implements Action {
    
    //用户名字
	 private   String nickname;
      private User user;
      private List<Applicant> applicantList;

  	public List<Applicant> getApplicantList() {
  		return applicantList;
  	}


  	public void setApplicantList(List<Applicant> applicantList) {
  		this.applicantList = applicantList;
  	}

  	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
     //好友列表
	private List<User> userList;
	public User getUser() {
		return user;
	}


	public List<User> getUserList() {
		return userList;
	}


	public void setUserList(List<User> userList) {
		this.userList = userList;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String execute() throws Exception {
		Map session = ActionContext.getContext().getSession();
		
		if(session.get("user") != null){
			this.setUser((User)session.get("user"));
			System.out.println(user.getUsername());
			this.setUserList(new FriendDaoImpl().list(user.getUsername()));
			this.setApplicantList(new ApplicantDaoImpl().list(user.getUsername()));
			 this.setNickname(user.getName());
			 
			return SUCCESS;
		     
		}
		return ERROR;
	}

	

	
	
	  
	//这是websocket代码
/*
	private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<IndexAction> connections =
            new CopyOnWriteArraySet();
   
    private Session session;

	    @OnOpen
	    public void start(Session session) {
	    	
	        this.session = session;
	        connections.add(this);
	        String message = String.format("* %s %s", this.getNickname(), "has joined.");
	        broadcast(message);
	    }

	    @OnClose
	    public void end() {
	        connections.remove(this);
	        String message = String.format("* %s %s",
	                nickname, "has disconnected.");
	        broadcast(message);
	    }

	    @OnMessage
	    public void incoming(String message) {
	        // Never trust the client
	    
	    	broadcast(message);
	    }

	    @OnError
	    public void onError(Throwable t) throws Throwable {
	        System.out.println("Chat Error: " + t.toString());
	    }

	    private  void broadcast(String msg) {
	        for (IndexAction client : connections) {
	            try {
	                synchronized (client) {
	                	 System.out.println(this.getNickname());
	                    client.session.getBasicRemote().sendText(this.getNickname()+":"+ msg);
	                }
	            } catch (IOException e) {
	            	System.out.println("Chat Error: Failed to send message to client");
	                connections.remove(client);
	                try {
	                    client.session.close();
	                } catch (IOException e1) {
	                    // Ignore
	                }
	                String message = String.format("* %s %s",
	                        client.nickname, "has been disconnected.");
	                broadcast(message);
	            }
	        }
	    }*/
	}
	


