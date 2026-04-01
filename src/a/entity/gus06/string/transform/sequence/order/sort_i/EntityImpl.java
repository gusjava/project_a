package a.entity.gus06.string.transform.sequence.order.sort_i;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160517";}
	
	public static final String DELIM = ";";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		Arrays.sort(n,String.CASE_INSENSITIVE_ORDER);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		b.append(n[i]+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
