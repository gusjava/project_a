package a.entity.gus.y.server1.engine.cmd.k.update;

import java.sql.Connection;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service knowledgeUpdate;
	private Service knowledgeEngine;

	public EntityImpl() throws Exception {
		knowledgeUpdate = Outside.service(this, "gus.y.knowledgedb1.knowledge.update");
		knowledgeEngine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception {
		Map args = (Map) obj;
		if(args.isEmpty()) throw new Exception("JSON manquant (utiliser :<json>)");
		knowledgeUpdate.p(new Object[]{cx(), args});
		return null;
	}

	private Connection cx() throws Exception
	{return (Connection) knowledgeEngine.r("cx");}
}