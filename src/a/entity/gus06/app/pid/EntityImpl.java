package a.entity.gus06.app.pid;

import java.lang.management.ManagementFactory;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140703";}
	
	
	private String pid;
	
	public Object g() throws Exception
	{
		if(pid==null) pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
		return pid;
	}
}
