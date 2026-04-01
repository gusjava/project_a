package a.entity.gus06.string.transform.line.insert.empty;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170829";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		b.append(n[i]+DELIM+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-2);
		return b.toString();
	}
}
