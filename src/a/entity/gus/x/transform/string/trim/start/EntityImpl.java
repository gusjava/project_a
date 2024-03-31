package a.entity.gus.x.transform.string.trim.start;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240113";}
	
	public static final String WHITE = " \t\n\r";

	public Object t(Object obj) throws Exception {
		String s = (String) obj;

		if (s == null)
			return null;
		if (s.equals(""))
			return "";

		int offset = findOffset(s);
		return s.substring(offset);
	}

	private int findOffset(String s) {
		int i = 0;
		int len = s.length();
		while (i < len && WHITE.indexOf(s.charAt(i)) >= 0)
			i++;
		return i;
	}
}