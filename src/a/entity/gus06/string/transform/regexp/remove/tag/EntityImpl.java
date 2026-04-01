package a.entity.gus06.string.transform.regexp.remove.tag;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201102";}
	
	public static final String REGEX = "<([^>])+>";

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		s = s.replaceAll(REGEX,"").trim();
		return s.replaceAll(" +"," ");
	}
	
}