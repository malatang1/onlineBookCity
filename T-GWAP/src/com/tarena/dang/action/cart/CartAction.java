package com.tarena.dang.action.cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.dang.action.BaseAction;
import com.tarena.dang.entity.CartItem;
import com.tarena.dang.service.CartFactory;
import com.tarena.dang.service.CartService;

public class CartAction extends BaseAction{
	//input
	private int id;//产品id
	private int qty;//数量
	
	CartService cart;
	//output
	private boolean ok = false;
	private List<CartItem> items = new ArrayList<CartItem>();
	private double cost;
	private double saveprice;
	
	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public void setItems(List<CartItem> items) {
		this.items = items;
	}


	public void setCost(double cost) {
		this.cost = cost;
	}


	public void setSaveprice(double saveprice) {
		this.saveprice = saveprice;
	}


	public double getCost() {
		return cost;
	}


	public double getSaveprice() {
		return saveprice;
	}


	public List<CartItem> getItems() {
		return items;
	}


	public String buy(){
		cart = CartFactory.getCart(httpsession);
		try {
			cart.add(id);
			ok = true;
		} catch (SQLException e) {
			e.printStackTrace();
			ok = false;
		}
		return "buy";//以json形式返回给ajax
	}
	
	public String findbuypros() throws SQLException{
		cart = CartFactory.getCart(httpsession);
		items=cart.getBuyPros();
		cost=cart.cost();
		saveprice=cart.saveprice();
		return "buypros";
	}
	public String findordermessage() throws SQLException{
		cart = CartFactory.getCart(httpsession);
		items=cart.getBuyPros();
		cost=cart.cost();
		return "message";
	}
	public String finddeletepros() throws SQLException{
		cart = CartFactory.getCart(httpsession);
		items=cart.getDeletePros();
		return "deletepros";
	}
	public String update() throws SQLException{
		cart = CartFactory.getCart(httpsession);
		cart.update(id, qty);
		return "update";
	}
	public String delete() throws SQLException{
		cart = CartFactory.getCart(httpsession);
		cart.delete(id);
		return "delete";
	}
	public String recover() throws SQLException{
		cart = CartFactory.getCart(httpsession);
		cart.recovery(id);
		return "recover";
	}
	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
