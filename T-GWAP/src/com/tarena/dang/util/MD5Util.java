package com.tarena.dang.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import sun.misc.BASE64Encoder;

public class MD5Util {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Scanner scan=new Scanner(System.in);
		while(true){
			System.out.print("ԭ�ģ�");
			String origStr=scan.nextLine();
			System.out.println("���ģ�"+encrypt(origStr));
			if(origStr.equals("quit")){
				break;
			}
		}
		
	}
	/**
	 * ����md5�㷨,����һ��ժҪ
	 * �����ص�:
	 * 	a,Ψһ��
	 * 	b,������
	 * @param origStr
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encrypt(String origStr) throws NoSuchAlgorithmException{
		MessageDigest md=MessageDigest.getInstance("md5");
		byte[] buff=md.digest(origStr.getBytes());
		BASE64Encoder encoder=new BASE64Encoder();
		encoder.encode(buff);
		return encoder.encode(buff);
	}
}
