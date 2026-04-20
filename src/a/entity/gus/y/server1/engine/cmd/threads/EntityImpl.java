package a.entity.gus.y.server1.engine.cmd.threads;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service threadsMap;

	public EntityImpl() throws Exception
	{
		threadsMap = Outside.service(this, "gus.x.threads.build.map");
	}

	public Object t(Object obj) throws Exception
	{return threadsMap.g();}
}
