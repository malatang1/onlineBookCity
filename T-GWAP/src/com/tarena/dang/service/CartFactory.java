package com.tarena.dang.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.tarena.dang.serviceImpl.CartServiceImpl;

public class CartFactory {
	//一个session只能使用cart对象
	public static CartService getCart(
			HttpSession session){
		CartService cart = (CartService)session.getAttribute("cart");
		if(cart == null){//如果session没有,新建一个
			cart = new CartServiceImpl();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
}
