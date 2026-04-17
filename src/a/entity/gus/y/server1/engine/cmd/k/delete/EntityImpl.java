package a.entity.gus.y.server1.engine.cmd.k.delete;

import java.sql.Connection;
import java.util.Map;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service knowledgeCx;
	private Service knowledgeDelete;

	public EntityImpl() throws Exception
	{
		knowledgeCx     = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		knowledgeDelete = Outside.service(this, "gus.y.knowledgedb1.knowledge.delete");
	}

	public Object t(Object obj) throws Exception
	{
		List args = (List) obj;
		if(args == null || args.isEmpty()) throw new Exception("id manquant");
		Long id = Long.parseLong(""+args.get(0));
		
		Connection cx = (Connection) knowledgeCx.g();
		boolean done = knowledgeDelete.f(new Object[]{cx, id});
		return done;
	}
}
