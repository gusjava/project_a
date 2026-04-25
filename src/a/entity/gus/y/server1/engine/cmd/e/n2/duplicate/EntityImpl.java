package a.entity.gus.y.server1.engine.cmd.e.n2.duplicate;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service entityDuplicate;
	private Service entityEngine;
	
	public EntityImpl() throws Exception {
		entityDuplicate = Outside.service(this, "gus.y.entitysys1.perform.entity.duplicate");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size() < 2) throw new Exception("Usage: e-duplicate <name0> <name1>");
		String name0 = (String) list.get(0);
		String name1 = (String) list.get(1);
		boolean done = (Boolean) entityDuplicate.f(new Object[]{entityEngine, name0, name1});
		return done ? "done" : "duplicate failed (entity not found or target already exists)";
	}
}
