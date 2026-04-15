package a.entity.gus.y.server1.engine.cmd.e.findall_desc;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service findAllDescList;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		findAllDescList = Outside.service(this, "gus.y.entitydb1.entity.findall.desc");
		entityEngine    = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{return findAllDescList.t(cx());}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}
}
