package a.entity.gus06.string.transform.format.java.string1a;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170905";}



	public EntityImpl() throws Exception
	{
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		s = s.replace("\\","\\\\")
			.replace("\"","\\\"")
			.replace("\n","\\n")
			.replace("\t","\\t")
		;
		
		return s;
	}
}
