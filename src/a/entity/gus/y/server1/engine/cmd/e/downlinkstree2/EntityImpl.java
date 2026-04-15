package a.entity.gus.y.server1.engine.cmd.e.downlinkstree2;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service downlinksTree2;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		downlinksTree2 = Outside.service(this, "gus.y.entitydb1.entity.downlinkstree2");
		entityEngine   = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size() < 2) throw new Exception("Usage: e-downlinkstree2 <entity> <maxDeep>");
		return downlinksTree2.t(new Object[]{cx(), (String) list.get(0), Integer.parseInt((String) list.get(1))});
	}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}
}
