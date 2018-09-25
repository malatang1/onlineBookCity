package com.tarena.dang.entity;

import java.io.Serializable;

public class Item implements Serializable{
	private int  id;
	private int  order_id;
	private int  product_id;
	private String  product_name;
	private double  dang_price;
	private int  product_num;
	private double  amount;
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getDang_price() {
		return dang_price;
	}
	public void setDang_price(double dang_price) {
		this.dang_price = dang_price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	
}
