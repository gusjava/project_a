package a.entity.gus06.string.transform.sequence.remove.random;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160620";}
	
	public static final String DELIM = ";";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		int c = (int) (Math.random()*n.length);
		
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<n.length;i++)
		if(i!=c) b.append(n[i]+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
