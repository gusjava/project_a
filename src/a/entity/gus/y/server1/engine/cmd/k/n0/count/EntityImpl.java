package a.entity.gus.y.server1.engine.cmd.k.n0.count;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service perform;
	private Service engine;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.knowledgedb1.knowledge.count");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{return perform.t(cx());}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}
