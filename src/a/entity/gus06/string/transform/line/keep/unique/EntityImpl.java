package a.entity.gus06.string.transform.line.keep.unique;

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
			if(!set1.contains(n[i])) set1.add(n[i]);
			else if(!set2.contains(n[i])) set2.add(n[i]);
		}
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		if(!set2.contains(n[i])) b.append(n[i]+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
