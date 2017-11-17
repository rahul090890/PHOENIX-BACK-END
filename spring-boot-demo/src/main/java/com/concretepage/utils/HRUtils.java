package com.concretepage.utils;

import java.util.Calendar;
import java.util.Random;

public class HRUtils {
	
	public static long createAUniqueId(String userId) {
		return DateUtils.getDateinYYYYMMDD() + userId.hashCode();
	}

}
