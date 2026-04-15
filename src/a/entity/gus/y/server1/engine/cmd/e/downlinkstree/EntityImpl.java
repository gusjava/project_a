package a.entity.gus.y.server1.engine.cmd.e.downlinkstree;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service downlinksTree;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		downlinksTree = Outside.service(this, "gus.y.entitydb1.entity.downlinkstree");
		entityEngine  = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size() < 2) throw new Exception("Usage: e-downlinkstree <entity> <maxDeep>");
		return downlinksTree.t(new Object[]{cx(), (String) list.get(0), Integer.parseInt((String) list.get(1))});
	}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}
}
