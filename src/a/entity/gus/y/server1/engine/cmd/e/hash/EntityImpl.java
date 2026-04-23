package a.entity.gus.y.server1.engine.cmd.e.hash;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260422";}

	private Service findHash;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		findHash = Outside.service(this, "gus.y.entitydb1.entity.hash.w_name");
		entityEngine     = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-hash <entity>");
		String name = (String) list.get(0);
		Object hash = findHash.t(new Object[]{cx(), name});
		if(hash == null) throw new Exception("Entity not found: " + name);
		return hash.toString();
	}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}
}
