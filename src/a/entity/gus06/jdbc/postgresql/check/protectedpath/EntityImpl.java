package a.entity.gus06.jdbc.postgresql.check.protectedpath;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190726";}
	
	public boolean f(Object obj) throws Exception
	{
		String path = (String) obj;
		
		if(path.equals("sys")) return true;
		if(path.equals("postgres")) return true;
		if(path.equals("information_schema"))  return true;
		if(path.equals("performance_schema"))  return true;
		
		if(path.contains("."))
		{
			if(path.startsWith("sys.")) return true;
			if(path.startsWith("postgres.")) return true;
			if(path.startsWith("information_schema."))  return true;
			if(path.startsWith("performance_schema."))  return true;
		}
		
		return false;
	}
}