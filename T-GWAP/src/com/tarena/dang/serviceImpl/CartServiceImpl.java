package com.tarena.dang.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.dang.dao.ProductDao;
import com.tarena.dang.daoImpl.JDBCProductDao;
import com.tarena.dang.entity.CartItem;
import com.tarena.dang.entity.Item;
import com.tarena.dang.entity.Product;
import com.tarena.dang.service.CartService;

public class CartServiceImpl implements CartService {
	private List<CartItem> items = new ArrayList<CartItem>();

	private ProductDao dao = new JDBCProductDao();

	public void add(int id) throws SQLException {

		for (CartItem item : items) {
			if (item.getPro().getId() == id) {

				int qty = item.getQty() + 1;
				item.setQty(qty);
				return;
			}
		}

		CartItem item = new CartItem();
		Product pro = dao.findById(id);
		item.setPro(pro);
		item.setQty(1);
		items.add(item);
	}

	public double cost() throws SQLException {
		double sum=0;
		for (CartItem item : items){
			if(item.isBuy()){
				sum+=item.getPro().getDang_price()*item.getQty();
			}
		}
		return sum;
	}
	public double saveprice() throws SQLException {
		double dang=0,sum=0;
		for (CartItem item : items){
			if(item.isBuy()){
				dang+=item.getPro().getDang_price()*item.getQty();
				sum+=item.getPro().getFixed_price()*item.getQty();
			}
		}
		return sum-dang;
	}
	public void delete(int id) throws SQLException {

		for (CartItem item : items) {
			if (item.getPro().getId() == id) {
				item.setBuy(false);
			}
		}
	}

	public List<CartItem> getBuyPros() throws SQLException {

		List<CartItem> list=new ArrayList<CartItem>();
		for (CartItem item : items) {
			if (item.isBuy()) {
				list.add(item);
			}
		}
		return list;
	}
	public List<Item> getBuyItems() throws SQLException {
		List<Item> list=new ArrayList<Item>();
		for (CartItem item : items) {
			if (item.isBuy()) {
				Item i=new Item();
				i.setProduct_id(item.getPro().getId());
				i.setProduct_name(item.getPro().getProduct_name());
				i.setDang_price(item.getPro().getDang_price());
				i.setProduct_num(item.getQty());
				i.setAmount(item.getPro().getDang_price()*item.getQty());
				list.add(i);
			}
		}
		return list;
	}

	public List<CartItem> getDeletePros() throws SQLException {

		List<CartItem> list=new ArrayList<CartItem>();
		for (CartItem item : items) {
			if (!item.isBuy()) {
				list.add(item);
			}
		}
		return list;
	}

	public void recovery(int id) throws SQLException {
		for (CartItem item : items) {
			if (item.getPro().getId() == id) {
				item.setBuy(true);
			}
		}

	}

	public void update(int id, int pnum) throws SQLException {

		for (CartItem item : items) {
			if (item.getPro().getId() == id) {
				item.setQty(pnum);
			}
		}
	}

	public void clear() throws SQLException {
		items.clear();
		
	}

}
