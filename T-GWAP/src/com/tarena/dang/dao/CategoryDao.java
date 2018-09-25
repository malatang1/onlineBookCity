package com.tarena.dang.dao;

import java.sql.SQLException;
import java.util.List;

import com.tarena.dang.entity.Category;

public interface CategoryDao {
	public List<Category> findAll() throws SQLException;
	public List<Category> findByParentId(int pid) throws SQLException;
}
