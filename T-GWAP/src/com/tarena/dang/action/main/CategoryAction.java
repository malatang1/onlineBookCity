package com.tarena.dang.action.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.dang.action.BaseAction;
import com.tarena.dang.dao.CategoryDao;
import com.tarena.dang.daoImpl.JDBCCategroyDao;
import com.tarena.dang.entity.Category;

public class CategoryAction extends BaseAction {
	private List<Category> cats;

	public List<Category> getCats() {
		return cats;
	}

	public void setCats(List<Category> cats) {
		this.cats = cats;
	}
	public String execute() throws Exception{
		CategoryDao dao=new JDBCCategroyDao();
		List<Category> all=dao.findAll();
		cats=findByParent(all, 1);
		for(Category c:cats){
			c.setSubCats(findByParent(all, c.getId()));
		}
		return "success";
	}
	public List<Category> findByParent(List<Category> all,int parentId){
		List<Category> list=new ArrayList<Category>();
		for(Category c:all){
			if(c.getParent_id()==parentId){
				list.add(c);
			}
		}
		return list;
	}
 }
