package a.entity.gus.y.server1.engine.cmd.e.n2.uplinkstree2;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service uplinksTree2;
	private Service entityEngine;

	public EntityImpl() throws Exception
	{
		uplinksTree2 = Outside.service(this, "gus.y.entitydb1.entity.uplinkstree2");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size() < 2) throw new Exception("Usage: e-uplinkstree2 <entity> <maxDepth>");
		String name = (String) list.get(0);
		int maxDepth = Integer.parseInt((String) list.get(1));
		
		return uplinksTree2.t(new Object[]{cx(), name, maxDepth});
	}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}
}
