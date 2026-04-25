package a.entity.gus.y.server1.engine.cmd.k.get;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service findGet;
	private Service knowledgeEngine;

	public EntityImpl() throws Exception {
		findGet         = Outside.service(this, "gus.y.knowledgesys1.find.get");
		knowledgeEngine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception {
		List list = (List) obj;
		if(list == null || list.size() < 2) throw new Exception("k-get: usage: k-get <table> <id>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		return findGet.t(new Object[]{cx(), table, id});
	}

	private Connection cx() throws Exception
	{return (Connection) knowledgeEngine.r("cx");}
}