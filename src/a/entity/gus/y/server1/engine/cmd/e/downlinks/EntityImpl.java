package a.entity.gus.y.server1.engine.cmd.e.downlinks;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service findDownLinks;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		findDownLinks = Outside.service(this, "gus.y.entitydb1.entity_link.find2.sorted");
		entityEngine  = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-downlinks <entity>");
		return findDownLinks.t(new Object[]{cx(), joinArgs(list)});
	}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}

	private String joinArgs(List args)
	{
		StringBuilder sb = new StringBuilder((String) args.get(0));
		for(int i=1; i<args.size(); i++) sb.append(" ").append(args.get(i));
		return sb.toString();
	}
}
