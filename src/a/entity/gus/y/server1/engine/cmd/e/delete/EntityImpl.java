package a.entity.gus.y.server1.engine.cmd.e.delete;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service entityDelete;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		entityDelete = Outside.service(this, "gus.y.entitysys1.perform.entity.delete");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-delete <name>");
		String name = (String) list.get(0);
		boolean done = (Boolean) entityDelete.f(new Object[]{entityEngine, name});
		return done ? "done" : "delete failed (entity not found or outside devId scope)";
	}
}
