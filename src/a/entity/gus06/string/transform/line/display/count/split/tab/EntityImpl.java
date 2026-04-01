package a.entity.gus06.string.transform.line.display.count.split.tab;

import a.framework.*;
import java.util.*;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160802";}
	
	public static final String DELIM = "\n";
	public static final String DELIM2 = "\t";
	public static final String DELIM3 = "\t";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		{
			String line = n[i];
			int count = split(line,DELIM3).length;
			b.append(count+DELIM2+line+DELIM);
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private String[] split(String s, String cut)
	{return s.split(Pattern.quote(cut),-1);}
}
