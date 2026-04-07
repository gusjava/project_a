package a.entity.gus.z.server1.engine;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260407";}

	public EntityImpl() throws Exception
	{
		
	}
	
	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		
		String output = "["+input+"]";
		return output;
	}
}
