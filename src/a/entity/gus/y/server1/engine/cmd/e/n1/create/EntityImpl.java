package a.entity.gus.y.server1.engine.cmd.e.n1.create;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service joinArgs;
	private Service entityCreate;
	private Service entityEngine;

	public EntityImpl() throws Exception
	{
		joinArgs = Outside.service(this,"gus.y.server1.tool.args.fullstring");
		entityCreate = Outside.service(this, "gus.y.entitysys1.perform.entity.create");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		boolean done = entityCreate.f(new Object[]{entityEngine, joinArgs(obj)});
		return done ? "done" : "create failed";
	}

	private String joinArgs(Object args) throws Exception
	{return (String) joinArgs.t(args);}
}
