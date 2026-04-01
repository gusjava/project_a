package a.entity.gus06.string.transform.regexp.lazywhite;

import a.framework.*;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190426";}
	
	public static final String META = "<([{\\^-=$!|]})?*+.>";
	public static final String WHITE = " \t\n\r";

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		StringBuffer b = new StringBuffer();
		
		boolean white = false;
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(WHITE.indexOf(c)>=0)
			{
				white = true;
			}
			else if(META.indexOf(c)>=0)
			{
				if(white) {b.append("\\s+");white=false;}
				b.append("\\"+c);
			}
			else
			{
				if(white) {b.append("\\s+");white=false;}
				b.append(c);
			}
		}
		
		if(white) b.append("\\s+");
		return b.toString();
	}
}
