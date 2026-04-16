package a.entity.gus.y.server1.engine.cmd.e.editremove;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service entityEditRemove;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		entityEditRemove = Outside.service(this, "gus.y.entitysys1.perform.entity.edit.remove");
		entityEngine     = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		Map json = (Map) obj;
		String name   = (String)  json.get("name");
		Integer start = Integer.parseInt(""+json.get("start"));
		Integer end   = Integer.parseInt(""+json.get("end"));
		boolean done = entityEditRemove.f(new Object[]{entityEngine, name, new int[]{start, end}});
		return done ? "done" : "edit failed";
	}
}
