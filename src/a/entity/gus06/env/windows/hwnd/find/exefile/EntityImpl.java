package a.entity.gus06.env.windows.hwnd.find.exefile;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180219";}


	private Service pidToExeFile;
	private Service findPid;
	
	public EntityImpl() throws Exception
	{
		pidToExeFile = Outside.service(this,"gus06.env.windows.pid.toexefile");
		findPid = Outside.service(this,"gus06.env.windows.hwnd.find.pid");
	}

	public Object t(Object obj) throws Exception
	{
		Object pid = findPid.t(obj);
	    	return pidToExeFile.t(pid);
	}
}
