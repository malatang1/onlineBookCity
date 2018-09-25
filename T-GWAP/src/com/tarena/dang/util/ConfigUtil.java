package com.tarena.dang.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ��config.properites�����ļ��Ĺ���
 */
public class ConfigUtil {
	private static Properties props = 
		new Properties();
	static{
		//�õ�����ConfigUtil������������
		ClassLoader loader =
			ConfigUtil.class.getClassLoader();
//		System.out.println("loader:" + loader.getClass().getName());
		InputStream ips = loader
		.getResourceAsStream("util/config.properties");
		try {
			props.load(ips);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		return props.getProperty(key);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getValue("EmployeeDAO"));

	}

}
