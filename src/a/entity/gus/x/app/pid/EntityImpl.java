package a.entity.gus.x.app.pid;

import a.framework.*;
import java.lang.management.ManagementFactory;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20240111";}

	private String pid;

	public Object g() throws Exception
	{
		if (pid == null) pid = find();
		return pid;
	}

	private String find()
	{return ManagementFactory.getRuntimeMXBean().getName().split("@")[0];}
}
