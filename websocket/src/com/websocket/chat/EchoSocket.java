package com.websocket.chat;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat1")
public class EchoSocket {
	@OnOpen
     public void open(Session session){
    	 System.out.println("sessionID:"+session.getId());
     }
	
	@OnMessage
	public void message(Session session,String msg){
		 System.out.println("客服端："+msg);
		 try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
