package com.tarena.dang.util;


public class Factory {
	public static Object getInstance(String type){
		Object obj = null;
		//����type(DAO�ӿڵ�����)��ѯconfig.properties
		//�ļ����ҵ���Ӧ��DAO������Ȼ��ͨ��������ƣ�����
		//DAOʵ����
		String className = ConfigUtil.getValue(type);
		try {
			//Ȼ��ͨ��������ƣ��������ʵ��
			obj = Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return obj;
	}
}
