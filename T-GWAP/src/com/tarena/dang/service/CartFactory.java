package com.tarena.dang.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.tarena.dang.serviceImpl.CartServiceImpl;

public class CartFactory {
	//һ��sessionֻ��ʹ��cart����
	public static CartService getCart(
			HttpSession session){
		CartService cart = (CartService)session.getAttribute("cart");
		if(cart == null){//���sessionû��,�½�һ��
			cart = new CartServiceImpl();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
}
