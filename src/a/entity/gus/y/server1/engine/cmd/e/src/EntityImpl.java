package a.entity.gus.y.server1.engine.cmd.e.src;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service findSrc;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		findSrc      = Outside.service(this, "gus.y.entitysys1.find.src");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-src <entity>");
		String name = (String) list.get(0);
		Object src = findSrc.t(new Object[]{entityEngine, name});
		if(src == null) throw new Exception("Entity not found: " + name);
		return src;
	}
}
