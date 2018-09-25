package com.tarena.dang.action.user;

import com.tarena.dang.action.BaseAction;
import com.tarena.dang.dao.UserDao;
import com.tarena.dang.daoImpl.JDBCUserDAO;
import com.tarena.dang.entity.User;
import com.tarena.dang.util.RegexTestHarnessV5Util;

public class CheckCodeAction extends BaseAction{
	//input
	private String code;
	private User user;
	//output
	private boolean ok = false;//��json���
	
	
	//ȫ�ֱ���
	private UserDao dao=new JDBCUserDAO();
	//ҵ����
	public String checkcode(){
		//���û������codeֵ��session��ֵ�ȶ�
		String scode = (String)session.get("code");
		if(code.equalsIgnoreCase(scode)){
			ok = true;//�����ȷ,��ok=true
		}else{
			ok = false;
		}
		return "success";//��json result���
	}
	public String checkemail() throws Exception{
		User dbuser=dao.findUserByOneColname("email", user.getEmail());
		System.out.println(dbuser);
		if(dbuser==null){
			System.out.println("�������");
			ok=true;
		}else{
			ok=false;
		}
		return "success";
	}
	public String verifyemail() throws Exception{
		if(code.equals("")){
			httpRequest.setAttribute("verifyerror", "����д��֤��");
			return "fail";
			//b704f7c8-3441-4c71-b95e-3e9d815f24bc-2
		}else if(!RegexTestHarnessV5Util.test(code, "^\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}-\\d+$")){
			httpRequest.setAttribute("verifyerror", "���������֤���ʽ����ȷ");
			return "fail";
		}
		String inputcode=code.substring(0, code.lastIndexOf("-"));
		long id=Long.parseLong(code.substring(code.lastIndexOf("-")+1));
		//���û������emailverifyֵ��session��ֵ�ȶ�
		User user=dao.findUserByOneColname("id", id);
		if(user==null){
			httpRequest.setAttribute("verifyerror", "��֤ʧ��");
			return "fail";
		}
		String scode = user.getEmail_verify_code();
		if(inputcode.equals(scode)){
			user.setIs_email_verify("Y");
			dao.update(user);
			return "finish";
		}else{
			httpRequest.setAttribute("verifyerror", "��֤ʧ��");
			return "fail";
		}
		
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
