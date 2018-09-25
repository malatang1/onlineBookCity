package com.tarena.dang.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;


public class DButil {
	private static DataSource dataSource=null;
	private static ThreadLocal<Connection> connLocal=new ThreadLocal<Connection>();
	static{
		Properties props=new Properties();
		try {
			props.load(DButil.class.getClassLoader().getResourceAsStream("dbcp.properties"));
			dataSource=BasicDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection() throws SQLException{
		Connection conn=connLocal.get();
		if(conn==null||conn.isClosed()){
			conn=dataSource.getConnection();
			connLocal.set(conn);
		}
		return conn;
	}
	public static void close(){
		Connection conn=connLocal.get();
		if( conn!= null){
			try {
				conn.close();
				connLocal.set(null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
