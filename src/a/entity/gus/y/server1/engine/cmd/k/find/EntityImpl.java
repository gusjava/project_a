package a.entity.gus.y.server1.engine.cmd.k.find;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service knowledgeFind;
	private Service knowledgeEngine;

	public EntityImpl() throws Exception {
		knowledgeFind   = Outside.service(this, "gus.y.knowledgedb1.knowledge.find");
		knowledgeEngine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception {
		Long id = Long.parseLong("" + obj);
		return knowledgeFind.t(new Object[]{cx(), id});
	}

	private Connection cx() throws Exception
	{return (Connection) knowledgeEngine.r("cx");}
}