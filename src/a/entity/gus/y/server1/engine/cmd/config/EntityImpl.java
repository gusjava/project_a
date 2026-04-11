package a.entity.gus.y.server1.engine.cmd.config;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private String config;

	public EntityImpl() throws Exception
	{
		config = (String) Outside.resource(this, "configid");
	}

	public Object t(Object obj) throws Exception {return config;}
}
