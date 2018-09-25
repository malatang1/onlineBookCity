package com.tarena.dang.action.user;

import com.opensymphony.xwork2.ActionInvocation;
import com.tarena.dang.action.BaseAction;
import com.tarena.dang.dao.UserDao;
import com.tarena.dang.daoImpl.JDBCUserDAO;
import com.tarena.dang.entity.User;
import com.tarena.dang.util.MD5Util;

public class LoginAction extends BaseAction{
	private User user;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String execute() throws Exception{
		UserDao dao=new JDBCUserDAO();
		User dbuser=dao.findUserByOneColname("email", user.getEmail());
		if(dbuser==null){
			String loginerror="” œ‰≤ª¥Ê‘⁄";
			httpsession.setAttribute("loginerror", loginerror);
			return "fail";
		}else{
			if(dbuser.getPassword().equals(MD5Util.encrypt(user.getPassword()))){
				dbuser.setLast_login_time(System.currentTimeMillis());
				dbuser.setLast_login_ip(httpRequest.getRemoteAddr());
				dao.update(dbuser);
				httpsession.setAttribute("user", dbuser);
				String url1=(String)httpsession.getAttribute("url");
				if(url1!=null){
					url=url1;
				}else{
					url="/main/main.jsp";
				}
				if(dbuser.getIs_email_verify().equals("N")){
					url="/user/verify_form.jsp";
				}
				System.out.println("url:"+url);
				return "success";
			}else{
				String loginerror="√‹¬Î¥ÌŒÛ";
				httpsession.setAttribute("loginerror", loginerror);
				return "fail";
			}
		}
	}
}
