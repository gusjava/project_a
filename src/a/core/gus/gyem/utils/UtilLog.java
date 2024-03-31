package a.core.gus.gyem.utils;

import java.util.Date;

public class UtilLog {

	public static void println(Object src, String m) {
		String timeStamp = UtilDate.timeStamp(new Date());
		String srcName = src.getClass().getName();
		System.out.println(timeStamp+"\t"+srcName+"\t"+m);
	}
}
