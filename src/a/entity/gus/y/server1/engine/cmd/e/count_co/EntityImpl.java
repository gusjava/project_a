package a.entity.gus.y.server1.engine.cmd.e.count_co;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service countCo;
	private Service entityEngine;
	private Service joinArgs;

	public EntityImpl() throws Exception {
		countCo = Outside.service(this, "gus.y.entitydb1.entity.count.co");
		joinArgs = Outside.service(this,"gus.y.server1.tool.args.fullstring");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{return countCo.t(new Object[]{cx(), joinArgs(obj)});}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}

	private String joinArgs(Object args) throws Exception
	{return (String) joinArgs.t(args);}
}
