package a.entity.gus.y.server1.engine.cmd.e.n0.creationdatebyname;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service perform;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		perform = Outside.service(this, "gus.y.entitydb1.entity.creationdatebyname");
		entityEngine           = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{return perform.t(cx());}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}
}
