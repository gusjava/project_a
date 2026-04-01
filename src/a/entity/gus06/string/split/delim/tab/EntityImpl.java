package a.entity.gus06.string.split.delim.tab;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220515";}
	

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.split(";");
	}
}