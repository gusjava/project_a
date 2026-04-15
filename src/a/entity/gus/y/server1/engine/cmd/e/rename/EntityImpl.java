package a.entity.gus.y.server1.engine.cmd.e.rename;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service entityRename;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		entityRename = Outside.service(this, "gus.y.entitysys1.perform.entity.rename");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size() < 2) throw new Exception("Usage: e-rename <name0> <name1>");
		String name0 = (String) list.get(0);
		String name1 = (String) list.get(1);
		boolean done = (Boolean) entityRename.f(new Object[]{entityEngine, name0, name1, true});
		return done ? "done" : "rename failed (entity not found or target already exists)";
	}
}
