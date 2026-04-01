package a.entity.gus06.string.transform.normalize.multiple.space;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250717";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String s = (String) obj;
		s = s.replaceAll(" +"," ");
		return s.trim();
	}
}