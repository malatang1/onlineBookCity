package com.tarena.dang.action.main;

import java.util.List;

import com.tarena.dang.action.BaseAction;
import com.tarena.dang.dao.ProductDao;
import com.tarena.dang.daoImpl.JDBCProductDao;
import com.tarena.dang.entity.Product;
import com.tarena.dang.entity.User;

public class MainAction extends BaseAction {
	private User user;
	//output
	private List<Product> pros;
	
	
	//全局变量
	private ProductDao dao=new JDBCProductDao();
	
	public List<Product> getPros() {
		return pros;
	}
	public void setPros(List<Product> pros) {
		this.pros = pros;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String findnew() throws Exception{
		pros=dao.findNew();
		return "new";
	}
	public String productlist() throws Exception{
		return "success";
	}
	public String loginout(){
		httpsession.invalidate();
		return "success";
	}
	public String findrecommend() throws Exception{
		pros=dao.findRecommend();
		return "recommend";
	}
	public String findhot() throws Exception{
		pros=dao.findHot();
		return "hot";
	}
	public String findsaled() throws Exception{
		pros=dao.findHot();
		return "saled";
	}
}
