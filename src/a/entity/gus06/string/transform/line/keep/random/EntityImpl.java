package a.entity.gus06.string.transform.line.keep.random;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160620";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		if(n.length==0) return "";
		int c = (int) (Math.random()*n.length);
		return n[c];
	}
}
