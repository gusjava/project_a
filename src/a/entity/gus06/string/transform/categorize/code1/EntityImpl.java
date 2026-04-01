package a.entity.gus06.string.transform.categorize.code1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151020";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		if(s==null) return "0";
		if(s.equals("")) return "1";
		return "2";
	}
}