package a.entity.gus.y.server1.engine.cmd.e.n2.uplinkstree;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service uplinksTree;
	private Service entityEngine;

	public EntityImpl() throws Exception
	{
		uplinksTree  = Outside.service(this, "gus.y.entitydb1.entity.uplinkstree");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size() < 2) throw new Exception("Usage: e-uplinkstree <entity> <maxDepth>");
		String name = (String) list.get(0);
		int maxDepth = Integer.parseInt((String) list.get(1));
		
		return uplinksTree.t(new Object[]{cx(), name, maxDepth});
	}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}
}
