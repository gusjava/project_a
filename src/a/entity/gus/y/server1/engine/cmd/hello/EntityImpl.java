package a.entity.gus.y.server1.engine.cmd.hello;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private String configId;

	public EntityImpl() throws Exception
	{
		configId = (String) Outside.resource(this,"configid");
	}

	public Object t(Object obj) throws Exception
	{return "Hello! Nice to meet you! I am "+configId+", a Java application based on A project.";}
}
