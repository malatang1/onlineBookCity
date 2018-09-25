package com.tarena.dang.util;

import java.util.UUID;

public class VerifyUtil {
	public static String createUUID(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
}
