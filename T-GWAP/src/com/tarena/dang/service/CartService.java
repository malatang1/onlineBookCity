package com.tarena.dang.service;

import java.sql.SQLException;
import java.util.List;

import com.tarena.dang.entity.CartItem;
import com.tarena.dang.entity.Item;

public interface CartService {

	public void add(int id) throws SQLException;

	public void delete(int id) throws SQLException;

	public void recovery(int id) throws SQLException;

	public void update(int id,int pnum) throws SQLException;

	public List<CartItem> getBuyPros() throws SQLException;
	
	public List<Item> getBuyItems() throws SQLException;

	public List<CartItem> getDeletePros() throws SQLException;

	public double cost() throws SQLException;
	public double saveprice() throws SQLException;
	public void clear() throws SQLException;
}
