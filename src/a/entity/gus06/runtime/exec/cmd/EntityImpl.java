package a.entity.gus06.runtime.exec.cmd;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180220";}
	
	public Object t(Object obj) throws Exception
	{return process((String) obj);}
	
	private Process process(String cmd) throws Exception
	{return Runtime.getRuntime().exec(cmd);}
}