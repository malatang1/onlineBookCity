package com.tarena.dang.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.dang.dao.OrderDao;
import com.tarena.dang.entity.Item;
import com.tarena.dang.entity.Order;
import com.tarena.dang.entity.ReceiveAddress;
import com.tarena.dang.util.DButil;

public class JDBCOrderDao implements OrderDao{

	public void saveOrder(Order order) throws SQLException {
		String sql="insert into d_order(user_id,status,order_time," +
				"order_desc,total_price,receive_name,full_address,postal_code,mobile,phone) " +
				"values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		pst.setInt(1, order.getReceiveAddress().getUser_id());
		pst.setInt(2, order.getStatus());
		pst.setLong(3, order.getOrder_time());
		pst.setString(4, order.getOrder_desc());
		pst.setDouble(5, order.getTotal_price());
		pst.setString(6, order.getReceiveAddress().getReceive_name());
		pst.setString(7, order.getReceiveAddress().getFull_address());
		pst.setString(8, order.getReceiveAddress().getPostal_code());
		pst.setString(9, order.getReceiveAddress().getMobile());
		pst.setString(10, order.getReceiveAddress().getPhone());
		pst.executeUpdate();
		for(Item item:order.getItems()){
			saveItem(item);
		}
		if(findReceive(order.getReceiveAddress().getUser_id(), order.getReceiveAddress().getReceive_name())==null){
			saveReceiveAddress(order.getReceiveAddress());
		}else{
			updateReceive(order.getReceiveAddress());
		}
		
	}
	public void saveItem(Item item) throws SQLException{
		String sql="insert into d_item(order_id,product_id,product_name,dang_price,product_num,amount) " +
				"values(?,?,?,?,?,?)";
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		pst.setInt(1, item.getOrder_id());
		pst.setInt(2, item.getProduct_id());
		pst.setString(3, item.getProduct_name());
		pst.setDouble(4, item.getDang_price());
		pst.setInt(5, item.getProduct_num());
		pst.setDouble(6, item.getAmount());
		pst.executeUpdate();
	}
	public void saveReceiveAddress(ReceiveAddress receiveAddress) throws SQLException{
		String sql="insert into d_receive_address(user_id,receive_name,full_address,postal_code,mobile,phone) " +
				"values(?,?,?,?,?,?)";
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		pst.setInt(1, receiveAddress.getUser_id());
		pst.setString(2, receiveAddress.getReceive_name());
		pst.setString(3, receiveAddress.getFull_address());
		pst.setString(4, receiveAddress.getPostal_code());
		pst.setString(5, receiveAddress.getMobile());
		pst.setString(6, receiveAddress.getPhone());
		pst.executeUpdate();
		
	}
	public int getLastId() throws SQLException {
		int lastId=1001;
		String sql="select id from d_order order by id desc";
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			lastId=rs.getInt("id");
		}
		return lastId;
	}
	public List<ReceiveAddress> findReceives(int user_id) throws SQLException {
		List<ReceiveAddress> list=new ArrayList<ReceiveAddress>();
		String sql="select * from d_receive_address where user_id=?";
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		pst.setInt(1, user_id);
		ResultSet rs=pst.executeQuery();
		while(rs.next()){
			ReceiveAddress receiveAddress=new ReceiveAddress();
			receiveAddress.setId(rs.getInt("id"));
			receiveAddress.setFull_address(rs.getString("full_address"));
			receiveAddress.setMobile(rs.getString("mobile"));
			receiveAddress.setPhone(rs.getString("phone"));
			receiveAddress.setPostal_code(rs.getString("postal_code"));
			receiveAddress.setReceive_name(rs.getString("receive_name"));
			receiveAddress.setUser_id(rs.getInt("user_id"));
			list.add(receiveAddress);
		}
		return list;
	}
	public ReceiveAddress findReceive(int id, int user_id) throws SQLException {
		ReceiveAddress addr =null;
		String sql = "select * from d_receive_address where user_id=?&&id=?";
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		pst.setInt(1, user_id);
		pst.setInt(2, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			addr = new ReceiveAddress();
			addr.setId(id);
			addr.setUser_id(user_id);
			addr.setReceive_name(rs.getString("receive_name"));
			addr.setFull_address(rs.getString("full_address"));
			addr.setPostal_code(rs.getString("postal_code"));
			addr.setMobile(rs.getString("mobile"));
			addr.setPhone(rs.getString("phone"));
		}
		return addr;
	}
	public ReceiveAddress findReceive(int user_id,String receive_name) throws SQLException {
		ReceiveAddress addr =null;
		String sql = "select * from d_receive_address where receive_name=?&&user_id=?";
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		pst.setString(1, receive_name);
		pst.setInt(2, user_id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			addr = new ReceiveAddress();
			addr.setUser_id(user_id);
			addr.setReceive_name(rs.getString("receive_name"));
			addr.setFull_address(rs.getString("full_address"));
			addr.setPostal_code(rs.getString("postal_code"));
			addr.setMobile(rs.getString("mobile"));
			addr.setPhone(rs.getString("phone"));
		}
		return addr;
	}
	public void updateReceive(ReceiveAddress receiveAddress) throws SQLException {
		String sql="update d_receive_address set full_address=?,postal_code=?,mobile=?,phone=? " +
				"where user_id="+receiveAddress.getUser_id()+"&&receive_name="+receiveAddress.getReceive_name();
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		pst.setString(1, receiveAddress.getFull_address());
		pst.setString(2, receiveAddress.getPostal_code());
		pst.setString(3, receiveAddress.getMobile());
		pst.setString(4, receiveAddress.getPhone());
		pst.executeUpdate();
	}

}
