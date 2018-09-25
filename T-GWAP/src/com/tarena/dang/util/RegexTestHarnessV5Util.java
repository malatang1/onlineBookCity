package com.tarena.dang.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTestHarnessV5Util {

	public static boolean test(String str, String reg) {

		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		boolean found = false;
		while (matcher.find()) {
			found = true;
		}
		if (!found) {
			return false;
		}
		return true;
	}
}