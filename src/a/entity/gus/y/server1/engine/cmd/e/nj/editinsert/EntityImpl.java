package a.entity.gus.y.server1.engine.cmd.e.nj.editinsert;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service entityEditInsert;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		entityEditInsert = Outside.service(this, "gus.y.entitysys1.perform.entity.edit.insert");
		entityEngine     = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		Map json = (Map) obj;
		String name  = (String)  json.get("name");
		Integer pos  = Integer.parseInt(""+json.get("pos"));
		String src   = (String)  json.get("src");
		boolean done = entityEditInsert.f(new Object[]{entityEngine, name, pos, src});
		return done ? "done" : "edit failed";
	}
}
