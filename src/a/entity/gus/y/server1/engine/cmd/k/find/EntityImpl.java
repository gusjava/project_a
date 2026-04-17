package a.entity.gus.y.server1.engine.cmd.k.find;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service knowledgeCx;
	private Service knowledgeFind;

	public EntityImpl() throws Exception
	{
		knowledgeCx   = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		knowledgeFind = Outside.service(this, "gus.y.knowledgedb1.knowledge.find");
	}

	public Object t(Object obj) throws Exception
	{
		Long id = Long.parseLong("" + obj);
		Connection cx = (Connection) knowledgeCx.g();
		return knowledgeFind.t(new Object[]{cx, id});
	}
}
