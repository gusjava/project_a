package a.entity.gus06.string.split.lines1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160604";}
	

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.replace("\r","").split("\n");
	}
}
