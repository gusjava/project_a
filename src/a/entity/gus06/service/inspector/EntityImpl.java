package a.entity.gus06.service.inspector;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201129";}


	private Service extract;
	private PrintStream out;
	
	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.service.extract.target");
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Service s = (Service) obj;
		Object target = extract.t(s);
		
		if(target!=null) out.println("Target found inside service: "+target.getClass());
		else out.println("No target found inside service ["+s.getClass()+"]");
	}
}