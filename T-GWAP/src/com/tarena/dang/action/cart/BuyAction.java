package com.tarena.dang.action.cart;

import java.sql.SQLException;

import com.tarena.dang.action.BaseAction;
import com.tarena.dang.service.CartFactory;
import com.tarena.dang.service.CartService;

public class BuyAction extends BaseAction{
	//input
	private int id;//产品id
	//output
	private boolean ok = false;
	
	public String execute(){
		CartService cart = CartFactory.getCart(httpsession);
		try {
			cart.add(id);
			ok = true;
		} catch (SQLException e) {
			e.printStackTrace();
			ok = false;
		}
		return "success";//以json形式返回给ajax
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
