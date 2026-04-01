package a.entity.gus06.string.transform.line.duplicate.last;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150927";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		int length = n.length;
		
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<length;i++)
		{
			b.append(n[i]+DELIM);
			b.append(n[i]+DELIM);
		}
		
		if(length>0) b.append(n[length-1]+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
