package com.tarena.dang.entity;

import java.io.Serializable;

public class CartItem implements Serializable{
	private Product pro;
	private int qty = 1;
	private boolean buy = true;//true¹ºÂò,falseÉ¾³ý
	public boolean isBuy() {
		return buy;
	}
	public void setBuy(boolean buy) {
		this.buy = buy;
	}
	public Product getPro() {
		return pro;
	}
	public void setPro(Product pro) {
		this.pro = pro;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}
