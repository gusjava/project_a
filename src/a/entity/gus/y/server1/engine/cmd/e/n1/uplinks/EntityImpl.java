package a.entity.gus.y.server1.engine.cmd.e.n1.uplinks;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service joinArgs;
	private Service findUplinks;
	private Service entityEngine;

	public EntityImpl() throws Exception
	{
		joinArgs     = Outside.service(this, "gus.y.server1.tool.args.fullstring");
		findUplinks  = Outside.service(this, "gus.y.entitydb1.entity_link.find1.sorted");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		return findUplinks.t(new Object[]{cx(), joinArgs(obj)});
	}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}

	private String joinArgs(Object args) throws Exception
	{return (String) joinArgs.t(args);}
}
