package a.entity.gus.y.server1.engine.cmd.k.n1.delete_k;

import java.sql.Connection;
import java.util.Map;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service knowledgeCx;
	private Service knowledgeDelete;
	private Service joinArgs;

	public EntityImpl() throws Exception
	{
		knowledgeCx     = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		knowledgeDelete = Outside.service(this, "gus.y.knowledgedb1.knowledge.delete");
		joinArgs     = Outside.service(this, "gus.y.server1.tool.args.fullstring");
	}

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) knowledgeCx.g();
		boolean done = knowledgeDelete.f(new Object[]{cx, joinArgs(obj)});
		return done ? "done" : "delete failed";
	}

	private String joinArgs(Object args) throws Exception
	{return (String) joinArgs.t(args);}
}
