package a.entity.gus.y.server1.engine.cmd.k.n0.codes;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260506";}

	private Service findCodes;
	private Service engine;

	public EntityImpl() throws Exception
	{
		findCodes = Outside.service(this, "gus.y.knowledgesys1.find.codes");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{return findCodes.t(cx());}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}