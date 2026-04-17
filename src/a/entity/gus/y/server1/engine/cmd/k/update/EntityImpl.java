package a.entity.gus.y.server1.engine.cmd.k.update;

import java.sql.Connection;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service knowledgeCx;
	private Service knowledgeUpdate;

	public EntityImpl() throws Exception
	{
		knowledgeCx     = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		knowledgeUpdate = Outside.service(this, "gus.y.knowledgedb1.knowledge.update");
	}

	public Object t(Object obj) throws Exception
	{
		Map args = (Map) obj;
		if(args.isEmpty()) throw new Exception("JSON manquant (utiliser :<json>)");
		Connection cx = (Connection) knowledgeCx.g();
		knowledgeUpdate.p(new Object[]{cx, args});
		return null;
	}
}
