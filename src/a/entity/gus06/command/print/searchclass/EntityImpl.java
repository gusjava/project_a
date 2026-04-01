package a.entity.gus06.command.print.searchclass;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140728";}


	private Service searchClass;
	private PrintStream out;
	
	public EntityImpl() throws Exception
	{
		searchClass = Outside.service(this,"gus06.java.searchclass.fromrt.preferred");
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String classpath = (String) searchClass.t(obj);
		out.println(classpath);
	}
}
