package a.entity.gus06.string.transform.normalize.whitespace;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160521";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String s = (String) obj;
		s = s.replaceAll("[ \t\f\n\r]+"," ");
		return s.trim();
	}
}
