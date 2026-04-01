package a.entity.gus06.filter.string.build.mdots.prepare;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160406";}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		if(s.contains("..."))
		{
			String[] n = s.split("\\.\\.\\.");
			if(n.length!=2) throw new Exception("Invalid rule: "+s);
			return new Object[]{n[0],n[1],Boolean.FALSE,Boolean.FALSE};
		}
		if(s.contains("..!"))
		{
			String[] n = s.split("\\.\\.!");
			if(n.length!=2) throw new Exception("Invalid rule: "+s);
			return new Object[]{n[0],n[1],Boolean.FALSE,Boolean.TRUE};
		}
		if(s.contains("!.."))
		{
			String[] n = s.split("!\\.\\.");
			if(n.length!=2) throw new Exception("Invalid rule: "+s);
			return new Object[]{n[0],n[1],Boolean.TRUE,Boolean.FALSE};
		}
		if(s.contains("!.!"))
		{
			String[] n = s.split("!\\.!");
			if(n.length!=2) throw new Exception("Invalid rule: "+s);
			return new Object[]{n[0],n[1],Boolean.TRUE,Boolean.TRUE};
		}
		throw new Exception("Invalid rule: "+s);
	}
}