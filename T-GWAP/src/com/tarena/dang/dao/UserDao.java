package com.tarena.dang.dao;


import com.tarena.dang.entity.User;

public interface UserDao {
	public void save(User user) throws Exception;
	public User findUserByOneColname(String colname,String val) throws Exception;
	public User findUserByOneColname(String colname,long val) throws Exception;
	public void update(User user) throws Exception;
}
