package a.entity.gus06.java.srccode.minify;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200421";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s==null) return null;
		
		return s.trim().replaceAll("\\s+"," ");
	}
}