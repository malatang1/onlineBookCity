package com.tarena.dang.util;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlConnectionUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	static{
		try{
		Properties props=new Properties();
		ClassLoader cl=MysqlConnectionUtils.class.getClassLoader();
		InputStream is=cl.getResourceAsStream("utils"+File.separator+"db.properties");
		//src/com/tarena/tts/db.properties
//		props.load(new FileInputStream("db.properties"));
		props.load(is);
		driver=props.getProperty("driver");
		url=props.getProperty("url");
		username=props.getProperty("username");
		password=props.getProperty("password");
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static Connection getConnection()throws Exception{
		Class.forName(driver);
		//获取连接
		//三个参数url,user,password
		Connection con=
			DriverManager.getConnection(
					url+
					"?useUnicode=true&characterEncoding=utf8",
					username,
					password);
		return con;
	}
	public static void close(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		MysqlConnectionUtils con=new MysqlConnectionUtils();
		System.out.println(con);
	}
}
