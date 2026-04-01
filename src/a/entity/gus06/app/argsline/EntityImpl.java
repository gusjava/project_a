package a.entity.gus06.app.argsline;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140703";}

	
	private String line;

	public EntityImpl() throws Exception
	{
		String[] args = (String[]) Outside.resource(this,"launch.args");
		line = toString(args);
	}
	
	
	public Object g() throws Exception
	{return line;}
	
	
	
	
	
	private String toString(String[] args)
	{
		if(args==null || args.length==0) return null;
		StringBuilder b = new StringBuilder(args[0]);
		for(int i=1;i<args.length;i++) b.append(" "+args[i]);
		return b.toString();
	}
}
