package a.entity.gus06.string.transform.character.order.permute;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("")) return "";
		int n = s.length();
		return s.substring(n-1,n) + s.substring(0,n-1);
	}
}
