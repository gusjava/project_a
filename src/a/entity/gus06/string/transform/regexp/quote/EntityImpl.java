package a.entity.gus06.string.transform.regexp.quote;

import a.framework.*;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160424";}
	
	public static final String META = "<([{\\^-=$!|]})?*+.>";

	
	
	public Object t(Object obj) throws Exception
	{
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
