package com.tarena.dang.entity;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable{
	 private int id;
	 private int status;
	 private long order_time;
	 private String order_desc;
	 private double total_price;
	 
	 private ReceiveAddress receiveAddress;
	 private List<Item> items;
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public ReceiveAddress getReceiveAddress() {
		return receiveAddress;
	}
	public void setReceiveAddress(ReceiveAddress receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrder_desc() {
		return order_desc;
	}
	public void setOrder_desc(String order_desc) {
		this.order_desc = order_desc;
	}
	public long getOrder_time() {
		return order_time;
	}
	public void setOrder_time(long order_time) {
		this.order_time = order_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	 
}
