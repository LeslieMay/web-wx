package com.swu.wechat.dao;

import java.util.List;

import com.swu.wechat.entity.Message;
import com.swu.wechat.entity.User;

public interface MessageDao {
   public boolean add(Message message);
   public boolean delmsg(Message message);
   
}
