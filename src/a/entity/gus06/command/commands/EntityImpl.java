package a.entity.gus06.command.commands;

import a.framework.*;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20140721";}

	private Service execute;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.command.execute");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		String line = (String) obj;
		String[] nn = line.split(";");
		for(String n:nn)
		{
			boolean done = execute.f(n);
			if(!done) return false;
		}
		return true;
	}
}