package com.tarena.dang.dao;

import java.sql.SQLException;
import java.util.List;

import com.tarena.dang.entity.Product;

public interface ProductDao {
	public List<Product> findNew() throws SQLException;
	public List<Product> findRecommend() throws SQLException;
	public List<Product> findHot() throws SQLException;
	public List<Product> findByCatId(int cid,int page,int size) throws SQLException;
	public int getMaxpage(int cid,int size) throws SQLException;
	public Product findById(int id) throws SQLException;
}
