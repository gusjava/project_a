package a.entity.gus.y.server1.engine.cmd.e.count;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service count;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		count      = Outside.service(this, "gus.y.entitydb1.entity.count");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{return count.t(cx());}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}
}
