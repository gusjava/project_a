package a.entity.gus.y.server1.engine.cmd.e.names_co;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service namesCo;
	private Service entityEngine;
	private Service joinArgs;

	public EntityImpl() throws Exception {
		namesCo      = Outside.service(this, "gus.y.entitydb1.entity.names.w_co");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
		joinArgs     = Outside.service(this, "gus.y.server1.tool.args.fullstring");
	}

	public Object t(Object obj) throws Exception
	{return namesCo.t(new Object[]{cx(), joinArgs(obj)});}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}

	private String joinArgs(Object args) throws Exception
	{return (String) joinArgs.t(args);}
}
