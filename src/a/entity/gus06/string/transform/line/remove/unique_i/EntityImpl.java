package a.entity.gus06.string.transform.line.remove.unique_i;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160620";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		Set set1 = new HashSet();
		Set set2 = new HashSet();
		
		for(int i=0;i<n.length;i++)
		{
			String a = format(n[i]);
			if(!set1.contains(a)) set1.add(a);
			else if(!set2.contains(a)) set2.add(a);
		}
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		{
			String a = format(n[i]);
			if(set2.contains(a)) b.append(n[i]+DELIM);
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private String format(String s)
	{return s.toLowerCase();}
}
