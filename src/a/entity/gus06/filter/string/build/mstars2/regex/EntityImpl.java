package a.entity.gus06.filter.string.build.mstars2.regex;

import a.framework.*;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161202";}
	
	public static final String META = "<([{\\^-=$!|]})?*+.>";

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			
			if(c=='*')
			{
				b.append(".*");
			}
			else if(c=='?')
			{
				b.append(".");
			}
			else if(META.indexOf(c)>=0)
			{
				b.append("\\");
				b.append(c);
			}
			else b.append(c);
		}
		return b.toString();
	}
}
