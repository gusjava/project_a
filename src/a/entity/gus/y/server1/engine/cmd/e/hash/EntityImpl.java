package a.entity.gus.y.server1.engine.cmd.e.hash;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260419";}

	private Service findSrc;
	private Service entityEngine;
	private Service buildHash;

	public EntityImpl() throws Exception {
		findSrc      = Outside.service(this, "gus.y.entitysys1.find.src");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
		buildHash     = Outside.service(this, "gus.y.entityhash1.src.build");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size()!=1) 
			throw new Exception("Usage: e-src <entity>");
			
		String name = (String) list.get(0);
		Object src = findSrc.t(new Object[]{entityEngine, name});
		if(src == null) throw new Exception("Entity not found: " + name);
		return buildHash.t(src);
	}
}
