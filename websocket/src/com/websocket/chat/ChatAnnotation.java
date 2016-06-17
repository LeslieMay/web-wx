package com.websocket.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.google.gson.Gson;
import com.lowagie.text.SplitCharacter;
import com.swu.wechat.Impl.MessageDaoImpl;
import com.swu.wechat.entity.Message;

import net.sf.json.JSONObject;


@ServerEndpoint(value = "/chat")
public class ChatAnnotation {


    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<ChatAnnotation> connections =
            new CopyOnWriteArraySet();
    private Map<String,Session> sessions =new HashMap<String,Session>();
    private   String nickname="";
    private  Session session;
    private String name;
   

    @OnOpen
    public void start(Session session) {
        this.session = session;
      String  username=session.getQueryString();
        nickname=username.split("=")[1];

        connections.add(this);
          
       
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
       Gson gson =new Gson();
        	Message msg=  gson.fromJson(message, Message.class);
        	 String Nmsg=msg.getMsg();
        	 String json="{name:"+nickname+","+"msg:"+Nmsg+",}";
        	 JSONObject node = new JSONObject();  
        	 node.put("name", nickname);
        	node.put("msg", Nmsg);
        	String msgjson= gson.toJson(node);
        
        	 try {
        		 this.session.getBasicRemote().sendText(msgjson);
				chat(msg.getTo(),msgjson);
			} catch (IOException e) {
				System.out.println("Chat Error: Failed to send message to client");
			}
 
         }
    	
    

    @OnError
    public void onError(Throwable t) throws Throwable {
        System.out.println("Chat Error: " + t.toString());
    }

    private  void broadcast(String msg) {
        for (ChatAnnotation client : connections) {
            try {
                synchronized (client) {
                	String Nmsg=msg;
                    client.session.getBasicRemote().sendText(Nmsg);
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
    }
    private  void chat(String to,String Nmsg) {
        for (ChatAnnotation client : connections) {
            try {
                synchronized (client) {
                	if(client.nickname.equals(to)){
                    client.session.getBasicRemote().sendText(Nmsg);
                	}
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
    }
}