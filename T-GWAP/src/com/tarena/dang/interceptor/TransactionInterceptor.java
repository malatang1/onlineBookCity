package com.tarena.dang.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tarena.dang.util.DButil;

public class TransactionInterceptor extends AbstractInterceptor{

	public String intercept(ActionInvocation invocation) throws Exception {
		String resultCode=null;
		try {
			System.out.println("开启事务控制");
			DButil.getConnection().setAutoCommit(false);
			resultCode=invocation.invoke();
			DButil.getConnection().commit();
			System.out.println("提交事务");
		} catch (Exception e) {
			e.printStackTrace();
			DButil.getConnection().rollback();
			System.out.println("回滚事务");
			throw e;
		}finally{
			DButil.close();
			System.out.println("关闭connection");
		}
		return resultCode;
	}

}
