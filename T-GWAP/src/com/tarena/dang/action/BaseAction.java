package com.tarena.dang.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

public class BaseAction  implements RequestAware,SessionAware,
ApplicationAware,ServletRequestAware,ServletResponseAware,ServletContextAware{
	protected Map<String,Object> request;
	protected Map<String,Object> session;
	protected Map<String,Object> application;
	protected HttpServletRequest httpRequest;
	protected HttpServletResponse httpResponse;
	protected ServletContext servletContext;
	protected HttpSession httpsession;
	public void setRequest(Map<String, Object> req) {
		request = req;
	}

	public void setSession(Map<String, Object> arg0) {
		session=arg0;
		
	}

	public void setApplication(Map<String, Object> arg0) {
		application=arg0;
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		httpRequest=arg0;
		httpsession=httpRequest.getSession();
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		httpResponse=arg0;
		
	}

	public void setServletContext(ServletContext arg0) {
		servletContext=arg0;
		
	}

}
