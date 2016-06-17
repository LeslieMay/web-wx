package com.swu.wechat.dao;

import java.util.List;

import com.swu.wechat.entity.User;

public interface UserDao {
   public User Login(String username,String password);
   public List<User> list();
   public boolean changePassword(String username,String password);
   public boolean addUser(User user);
   public boolean delUser(User user);
}
