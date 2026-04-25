package a.entity.gus.y.server1.engine.cmd.k.list;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service findList;
	private Service knowledgeEngine;

	public EntityImpl() throws Exception {
		findList        = Outside.service(this, "gus.y.knowledgesys1.find.list");
		knowledgeEngine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception {
		List list = (List) obj;
		if(list == null || list.isEmpty()) throw new Exception("k-list: usage: k-list <table> [limit]");
		String table = (String) list.get(0);
		String limit = list.size() >= 2 ? (String) list.get(1) : "20";
		return findList.t(new Object[]{cx(), table, limit});
	}

	private Connection cx() throws Exception
	{return (Connection) knowledgeEngine.r("cx");}
}