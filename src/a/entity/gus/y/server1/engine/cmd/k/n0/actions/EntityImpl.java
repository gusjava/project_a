package a.entity.gus.y.server1.engine.cmd.k.n0.actions;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260506";}

	private Service findActions;
	private Service engine;

	public EntityImpl() throws Exception
	{
		findActions = Outside.service(this, "gus.y.knowledgesys1.find.actions");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{return findActions.t(cx());}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}