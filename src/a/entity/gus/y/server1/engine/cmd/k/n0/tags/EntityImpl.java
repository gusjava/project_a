package a.entity.gus.y.server1.engine.cmd.k.n0.tags;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service findTags;
	private Service engine;

	public EntityImpl() throws Exception
	{
		findTags = Outside.service(this, "gus.y.knowledgesys1.find.tags");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{return findTags.t(cx());}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}