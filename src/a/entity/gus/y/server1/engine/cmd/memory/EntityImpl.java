package a.entity.gus.y.server1.engine.cmd.memory;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service memoryMap;

	public EntityImpl() throws Exception
	{
		memoryMap = Outside.service(this, "gus.x.jvm.memory.map");
	}

	public Object t(Object obj) throws Exception {return memoryMap.g();}
}
