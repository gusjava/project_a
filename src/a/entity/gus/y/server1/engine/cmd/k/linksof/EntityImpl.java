package a.entity.gus.y.server1.engine.cmd.k.linksof;

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
		if(list == null || list.size() < 2) throw new Exception("k-links-of: usage: k-links-of <table> <id>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		String sql   = "SELECT * FROM " + table + "_link WHERE ID_LINKER = " + id + " OR ID_LINKED = " + id;
		Connection cx = (Connection) knowledgeCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
	}
}