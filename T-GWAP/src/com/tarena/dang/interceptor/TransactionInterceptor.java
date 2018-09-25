package com.tarena.dang.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tarena.dang.util.DButil;

public class TransactionInterceptor extends AbstractInterceptor{

	public String intercept(ActionInvocation invocation) throws Exception {
		String resultCode=null;
		try {
			System.out.println("�����������");
			DButil.getConnection().setAutoCommit(false);
			resultCode=invocation.invoke();
			DButil.getConnection().commit();
			System.out.println("�ύ����");
		} catch (Exception e) {
			e.printStackTrace();
			DButil.getConnection().rollback();
			System.out.println("�ع�����");
			throw e;
		}finally{
			DButil.close();
			System.out.println("�ر�connection");
		}
		return resultCode;
	}

}
