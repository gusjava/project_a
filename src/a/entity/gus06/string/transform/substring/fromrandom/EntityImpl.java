package a.entity.gus06.string.transform.substring.fromrandom;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150926";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		int l = s.length();
		if(l==0) return "";
		
		int c = (int) (Math.random()*l);
		
		if(c==l-1) return "";
		return s.substring(c+1);
	}
}
