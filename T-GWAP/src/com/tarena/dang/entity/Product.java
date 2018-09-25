package com.tarena.dang.entity;

import java.io.Serializable;

public class Product implements Serializable{
	private int id;
	private String  product_name;
	private String  description;
	private long  add_time;
	private double  fixed_price;
	private double  dang_price;
	private String  keywords;
	private int  has_deleted;
	private String  product_pic;
	public long getAdd_time() {
		return add_time;
	}
	public void setAdd_time(long add_time) {
		this.add_time = add_time;
	}
	public double getDang_price() {
		return dang_price;
	}
	public void setDang_price(double dang_price) {
		this.dang_price = dang_price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getFixed_price() {
		return fixed_price;
	}
	public void setFixed_price(double fixed_price) {
		this.fixed_price = fixed_price;
	}
	public int getHas_deleted() {
		return has_deleted;
	}
	public void setHas_deleted(int has_deleted) {
		this.has_deleted = has_deleted;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_pic() {
		return product_pic;
	}
	public void setProduct_pic(String product_pic) {
		this.product_pic = product_pic;
	}
	
}
