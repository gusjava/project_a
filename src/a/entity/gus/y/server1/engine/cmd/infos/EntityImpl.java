package a.entity.gus.y.server1.engine.cmd.infos;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service infoMap;

	public EntityImpl() throws Exception
	{
		infoMap = Outside.service(this, "gus06.app.infomap");
	}

	public Object t(Object obj) throws Exception {return infoMap.g();}
}
