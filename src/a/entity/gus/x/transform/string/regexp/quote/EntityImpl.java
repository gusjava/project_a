package a.entity.gus.x.transform.string.regexp.quote;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240714";}
	
	public static final String META = "<([{\\^-=$!|]})?*+.>";
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String s = (String) obj;
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(META.indexOf(c)>=0) b.append("\\");
			b.append(c);
		}
		return b.toString();
	}
}
