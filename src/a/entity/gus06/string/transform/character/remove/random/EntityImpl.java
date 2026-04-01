package a.entity.gus06.string.transform.character.remove.random;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150926";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		int l = s.length();
		if(l==0) return "";
		
		int c = (int) (Math.random()*l);
		
		if(c==0) return s.substring(1);
		if(c==l-1) return s.substring(0,c);
		return s.substring(0,c)+s.substring(c+1);
	}
}
