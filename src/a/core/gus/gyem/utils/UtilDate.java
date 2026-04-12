package a.core.gus.gyem.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDate {

	public static String yyyymmdd_hhmmss(Date date) {
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);
	}
	
	public static String timeStamp(Date date) {
		return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(date);
	}
}
