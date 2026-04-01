package a.entity.gus06.system.err.manager;

import a.framework.*;
import java.io.*;

public class EntityImpl implements Entity, P, G {

	public String creationDate() {return "20140730";}

	private Service factory;
	private PrintStream p1;
	

	public EntityImpl() throws Exception
	{
		factory = Outside.service(this,"gus06.io.printstream.factory.multi.hist");
		
		p1 = (PrintStream) factory.g();
		p(System.err);
		
		System.setErr(p1);
	}
	
	public Object g() throws Exception
	{return p1;}
	
	
	public void p(Object obj) throws Exception
	{((V)p1).v("add",obj);}
}
