package a.entity.gus.y.server1.engine.cmd.e.create;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service entityCreate;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		entityCreate = Outside.service(this, "gus.y.entitysys1.perform.entity.create");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-create <entity> [features]");
		boolean done = entityCreate.f(new Object[]{entityEngine, joinArgs(list)});
		return done ? "done" : "create failed";
	}

	private String joinArgs(List args)
	{
		StringBuilder sb = new StringBuilder((String) args.get(0));
		for(int i=1; i<args.size(); i++) sb.append(" ").append(args.get(i));
		return sb.toString();
	}
}
