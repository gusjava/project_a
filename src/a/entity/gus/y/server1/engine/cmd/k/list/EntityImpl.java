package a.entity.gus.y.server1.engine.cmd.k.list;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service knowledgeCx;
	private Service sqlSelect;

	public EntityImpl() throws Exception
	{
		knowledgeCx = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		sqlSelect   = Outside.service(this, "gus.y.knowledgedb1.sql.select");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.isEmpty()) throw new Exception("k-list: usage: k-list <table> [limit]");
		String table = (String) list.get(0);
		int limit    = list.size() >= 2 ? Integer.parseInt((String) list.get(1)) : 20;
		String sql   = "SELECT * FROM " + table + " ORDER BY date_created DESC LIMIT " + limit;
		Connection cx = (Connection) knowledgeCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
	}
}