package com.tarena.dang.serviceImpl;

import com.tarena.dang.dao.UserDao;
import com.tarena.dang.daoImpl.JDBCUserDAO;
import com.tarena.dang.entity.User;
import com.tarena.dang.service.UserService;
import com.tarena.dang.util.Constant;
import com.tarena.dang.util.EmailUtil;
import com.tarena.dang.util.MD5Util;
import com.tarena.dang.util.VerifyUtil;

public class UserServiceImpl implements UserService {

	public void register(User user) throws Exception {
		String uuid=VerifyUtil.createUUID();
		user.setPassword(MD5Util.encrypt(user.getPassword()));
		user.setUser_integral(Constant.NORMAL);
		user.setIs_email_verify("N");
		user.setEmail_verify_code(uuid);
		user.setLast_login_time(System.currentTimeMillis());
		UserDao dao=new JDBCUserDAO();
		dao.save(user);
		String code=uuid+"-"+user.getId();
		EmailUtil.send(user.getEmail(), code);
	}

}
