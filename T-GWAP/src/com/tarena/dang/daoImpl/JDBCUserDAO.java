package com.tarena.dang.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tarena.dang.dao.UserDao;
import com.tarena.dang.entity.User;
import com.tarena.dang.util.DButil;
import com.tarena.dang.util.MD5Util;

public class JDBCUserDAO implements UserDao {

	public void save(User user) throws Exception {
		String sql="insert into d_user(email,nickname,password,user_integral,is_email_verify," +
				"email_verify_code,last_login_time,last_login_ip) values(?,?,?,?,?,?,?,?)";
		Connection con=DButil.getConnection();
		PreparedStatement pst=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, user.getEmail());
		pst.setString(2, user.getNickname());
		pst.setString(3, user.getPassword());
		pst.setInt(4, user.getUser_integral());
		pst.setString(5, user.getIs_email_verify());
		pst.setString(6, user.getEmail_verify_code());
		pst.setLong(7, user.getLast_login_time());
		pst.setString(8, user.getLast_login_ip());
		pst.executeUpdate();
		ResultSet rs=pst.getGeneratedKeys();
		rs.next();
		int id=rs.getInt(1);
		user.setId(id);
	}

	public User findUserByOneColname(String colname,String value) throws Exception {
		User user=null;
		String sql="select * from d_user where "+colname+"=?";
		Connection con=DButil.getConnection();
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1, value);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			user=new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setNickname(rs.getString("nickname"));
			user.setPassword(rs.getString("password"));
			user.setUser_integral(rs.getInt("user_integral"));
			user.setIs_email_verify(rs.getString("is_email_verify"));
			user.setEmail_verify_code(rs.getString("email_verify_code"));
			user.setLast_login_time(rs.getLong("last_login_time"));
			user.setLast_login_ip(rs.getString("last_login_ip"));
		}
		return user;
	}
	public User findUserByOneColname(String colname,long value) throws Exception {
		User user=null;
		String sql="select * from d_user where "+colname+"=?";
		Connection con=DButil.getConnection();
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setLong(1, value);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			user=new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setNickname(rs.getString("nickname"));
			user.setPassword(rs.getString("password"));
			user.setUser_integral(rs.getInt("user_integral"));
			user.setIs_email_verify(rs.getString("is_email_verify"));
			user.setEmail_verify_code(rs.getString("email_verify_code"));
			user.setLast_login_time(rs.getLong("last_login_time"));
			user.setLast_login_ip(rs.getString("last_login_ip"));
		}
		return user;
	}

	public void update(User user) throws Exception {
		String sql="update d_user set last_login_time=?,last_login_ip=?,password=?," +
				"user_integral=?,is_email_verify=?,email_verify_code=?,nickname=? where email=?";
		Connection con=DButil.getConnection();
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setLong(1, user.getLast_login_time());
		pst.setString(2, user.getLast_login_ip());
		pst.setString(3, user.getPassword());
		pst.setInt(4, user.getUser_integral());
		pst.setString(5, user.getIs_email_verify());
		pst.setString(6, user.getEmail_verify_code());
		pst.setString(7, user.getNickname());
		pst.setString(8, user.getEmail());
		pst.executeUpdate();
	}

}
