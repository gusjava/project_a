package a.entity.gus06.command.print.entitydate;

import java.io.PrintStream;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140827";}


	private Service retrieveSrcCode;
	private Service extractDate;
	private Service checkName;
	private PrintStream out;
	
	
	public EntityImpl() throws Exception
	{
		retrieveSrcCode = Outside.service(this,"gus06.entitydev.retrieve.srccode1");
		extractDate = Outside.service(this,"gus06.java.srccode.extract.entity.creationdate");
		checkName = Outside.service(this,"gus06.entitydev.entityname.check.existing");
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		String entityName = (String) checkName.t(obj);
		String src = (String) retrieveSrcCode.t(entityName);
		String date = (String) extractDate.t(src);
		
		out.println(date);
	}
}
