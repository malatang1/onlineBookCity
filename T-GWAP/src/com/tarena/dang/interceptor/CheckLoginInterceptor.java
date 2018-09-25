package com.tarena.dang.interceptor;


import java.io.File;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckLoginInterceptor extends AbstractInterceptor {
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String,Object> session=invocation.getInvocationContext().getSession();
		System.out.println(session.get("user"));
		if(session.get("user")==null){
			String url=ServletActionContext.getRequest().getServletPath()+".jsp";
			session.put("url", url);
			return "login";
		}else{
			return invocation.invoke();
		}
	}

}
