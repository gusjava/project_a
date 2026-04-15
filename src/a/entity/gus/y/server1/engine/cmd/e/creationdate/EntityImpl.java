package a.entity.gus.y.server1.engine.cmd.e.creationdate;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service findCreationDate;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		findCreationDate = Outside.service(this, "gus.y.entitydb1.entity.find.creationdate");
		entityEngine     = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-creationdate <entity>");
		String name = (String) list.get(0);
		Object creationDate = findCreationDate.t(new Object[]{cx(), name});
		if(creationDate == null) throw new Exception("Entity not found: " + name);
		return creationDate.toString();
	}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}
}
