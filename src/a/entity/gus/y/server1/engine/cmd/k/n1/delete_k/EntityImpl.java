package a.entity.gus.y.server1.engine.cmd.k.n1.delete_k;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service knowledgeDelete;
	private Service knowledgeEngine;
	private Service joinArgs;

	public EntityImpl() throws Exception {
		knowledgeDelete = Outside.service(this, "gus.y.knowledgedb1.knowledge.delete");
		knowledgeEngine = Outside.service(this, "gus.y.knowledgesys1.engine");
		joinArgs        = Outside.service(this, "gus.y.server1.tool.args.fullstring");
	}

	public Object t(Object obj) throws Exception {
		boolean done = knowledgeDelete.f(new Object[]{cx(), joinArgs(obj)});
		return done ? "done" : "delete failed";
	}

	private Connection cx() throws Exception
	{return (Connection) knowledgeEngine.r("cx");}

	private String joinArgs(Object args) throws Exception
	{return (String) joinArgs.t(args);}
}