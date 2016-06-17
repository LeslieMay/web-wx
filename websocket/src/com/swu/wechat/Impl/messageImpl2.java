package com.swu.wechat.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.swu.wechat.dao.ApplicantDao;
import com.swu.wechat.entity.Applicant;
import com.swu.wechat.entity.Message;
import com.swu.wechat.tool.DbHelp;


public class messageImpl2 {
	//插入消息
		public boolean add(Message message) {
			boolean flag = false;
			String sql ="insert into message(from,to,msg,date) values(?,?,?,?)";
			Object[] arr = {message.getFrom(),message.getTo(),message.getMsg(),message.getDate()};
			if(DbHelp.executeUpdata(sql, arr)>0){
				flag = true;
			}
			return flag;
			
		}
		

		//删除消息
		public boolean delmsg(Message message) {
			boolean flag = false;
			String sql = "delete from message where to= ?";
			Object[] arr = {message.getTo()};
			if(DbHelp.executeUpdata(sql, arr)>0){
				flag = true;
			}
			return flag;
		}

		@Test
		public void test(){
			Message msg=new Message();
			msg.setFrom("123123");
			msg.setTo("sfa");
			msg.setMsg("ffsafas");
			msg.setDate("fasfasfasfas");
			add(msg);
		}
}
