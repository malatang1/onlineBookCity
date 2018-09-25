package com.tarena.dang.action.main;

import java.sql.SQLException;
import java.util.List;

import com.tarena.dang.action.BaseAction;
import com.tarena.dang.dao.CategoryDao;
import com.tarena.dang.dao.ProductDao;
import com.tarena.dang.daoImpl.JDBCCategroyDao;
import com.tarena.dang.daoImpl.JDBCProductDao;
import com.tarena.dang.entity.Category;
import com.tarena.dang.entity.Product;

public class BooklistAction extends BaseAction {
	//input
	private int cid;//当前类别id
	private int pid;
	private int page=1;
	private int size=3;
	//output
	private List<Category> cats;
	private List<Product> pros;
	private int maxpage=1;
	private int totalnum;
	public String execute() throws Exception{
		CategoryDao dao=new JDBCCategroyDao();
		cats=dao.findByParentId(pid);
		for(Category c:cats){
			totalnum+=c.getPnum();
		}
		ProductDao productdao=new JDBCProductDao();
		pros=productdao.findByCatId(cid,page,size);
		maxpage=productdao.getMaxpage(cid, size);
		return "success";
	}
	public List<Category> getCats() {
		return cats;
	}
	public void setCats(List<Category> cats) {
		this.cats = cats;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public List<Product> getPros() {
		return pros;
	}
	public void setPros(List<Product> pros) {
		this.pros = pros;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getMaxpage() {
		return maxpage;
	}
	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}
	
}
