package a.entity.gus06.data.perform.exec.tostring.utf8;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170410";}


	private Service exec;
	private Service processToString;


	public EntityImpl() throws Exception
	{
		exec = Outside.service(this,"gus06.data.perform.exec");
		processToString = Outside.service(this,"gus06.process.tostring.utf8");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Process p = (Process) exec.t(obj);
		return processToString.t(p);
	}
}
