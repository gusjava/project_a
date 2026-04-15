package a.entity.gus.y.server1.engine.cmd.e.names;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service names;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		names      = Outside.service(this, "gus.y.entitydb1.entity.findall.names");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{return names.t(cx());}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}
}
