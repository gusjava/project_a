package a.entity.gus06.dir.perform.remove.ask;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20140910";}


	private Service input;
	private Service remove;


	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.confirm.dialog");
		remove = Outside.service(this,"gus06.dir.perform.remove");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(!dir.isDirectory())
			throw new Exception("Invalid directory: "+dir);
		
		boolean r = input.f("Are you sure to delete this directory ?");
		if(!r) return false;
		
		remove.p(dir);
		return true;
	}
}
