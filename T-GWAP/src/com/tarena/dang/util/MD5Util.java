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
			System.out.print("原文：");
			String origStr=scan.nextLine();
			System.out.println("密文："+encrypt(origStr));
			if(origStr.equals("quit")){
				break;
			}
		}
		
	}
	/**
	 * 依据md5算法,生成一个摘要
	 * 两个特点:
	 * 	a,唯一性
	 * 	b,不可逆
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
