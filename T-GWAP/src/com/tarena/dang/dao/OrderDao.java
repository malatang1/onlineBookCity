package com.tarena.dang.dao;

import java.sql.SQLException;
import java.util.List;

import com.tarena.dang.entity.Order;
import com.tarena.dang.entity.ReceiveAddress;

public interface OrderDao {
	public void saveOrder(Order order) throws SQLException;
	public int getLastId() throws SQLException;
	public List<ReceiveAddress> findReceives(int user_id) throws SQLException;
	public ReceiveAddress findReceive(int id,int user_id) throws SQLException;
	public void updateReceive(ReceiveAddress receiveAddress) throws SQLException;
}
