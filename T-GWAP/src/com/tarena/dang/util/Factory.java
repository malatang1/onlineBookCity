package com.tarena.dang.util;


public class Factory {
	public static Object getInstance(String type){
		Object obj = null;
		//依据type(DAO接口的名称)查询config.properties
		//文件，找到对应的DAO类名，然后通过反射机制，创建
		//DAO实例。
		String className = ConfigUtil.getValue(type);
		try {
			//然后通过反射机制，创建类的实例
			obj = Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return obj;
	}
}
