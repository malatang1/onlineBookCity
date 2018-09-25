package com.tarena.dang.action.order;

import java.sql.SQLException;
import java.util.List;

import com.tarena.dang.action.BaseAction;
import com.tarena.dang.dao.OrderDao;
import com.tarena.dang.daoImpl.JDBCOrderDao;
import com.tarena.dang.entity.Order;
import com.tarena.dang.entity.ReceiveAddress;
import com.tarena.dang.entity.User;
import com.tarena.dang.service.CartFactory;
import com.tarena.dang.service.CartService;

public class OrderAction extends BaseAction {
	private int id;
	private Order order;
	private ReceiveAddress receiveAddress;
	
	private List<ReceiveAddress> receives;
	
	public List<ReceiveAddress> getReceives() {
		return receives;
	}

	public void setReceives(List<ReceiveAddress> receives) {
		this.receives = receives;
	}

	public ReceiveAddress getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(ReceiveAddress receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String saveorder() throws SQLException{
		order=new Order();
		OrderDao dao=new JDBCOrderDao();
		CartService cart=CartFactory.getCart(httpsession);
		order.setId(dao.getLastId()+1);
		order.setItems(cart.getBuyItems());
		order.setOrder_time(System.currentTimeMillis());
		order.setOrder_desc("");
		order.setStatus(1);
		order.setTotal_price(cart.cost());
		User user=(User)httpsession.getAttribute("user");
		receiveAddress.setUser_id(user.getId());
		order.setReceiveAddress(receiveAddress);
		
		try {
			dao.saveOrder(order);
			cart.clear();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "saveorder";
	}
	public String getReceiveList() throws SQLException{
		OrderDao dao=new JDBCOrderDao();
		User user=(User)httpsession.getAttribute("user");
		receives=dao.findReceives(user.getId());
		return "receives";
	}
	public String getReceive() throws SQLException{
		OrderDao dao=new JDBCOrderDao();
		User user=(User)httpsession.getAttribute("user");
		receiveAddress=dao.findReceive(id,user.getId());
		return "receive";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
