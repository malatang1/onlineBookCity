package com.tarena.dang.action.user;

import java.sql.SQLException;

import com.tarena.dang.action.BaseAction;
import com.tarena.dang.dao.UserDao;
import com.tarena.dang.daoImpl.JDBCUserDAO;
import com.tarena.dang.entity.User;
import com.tarena.dang.service.UserService;
import com.tarena.dang.serviceImpl.UserServiceImpl;
import com.tarena.dang.util.Constant;
import com.tarena.dang.util.EmailUtil;
import com.tarena.dang.util.MD5Util;
import com.tarena.dang.util.VerifyUtil;

public class RegisterAction extends BaseAction {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String execute() throws Exception{
		user.setLast_login_ip(httpRequest.getRemoteAddr());
		String uuid=VerifyUtil.createUUID();
		user.setPassword(MD5Util.encrypt(user.getPassword()));
		user.setUser_integral(Constant.NORMAL);
		user.setIs_email_verify("N");
		user.setEmail_verify_code(uuid);
		user.setLast_login_time(System.currentTimeMillis());
		UserDao dao=new JDBCUserDAO();
		dao.save(user);
		httpsession.setAttribute("user", user);
		String code=uuid+"-"+user.getId();
		EmailUtil.send(user.getEmail(), code);
		return "verify";
	}
}
