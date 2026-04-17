package a.entity.gus.y.server1.engine.cmd.k.removelink;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service knowledgeCx;
	private Service sqlDelete;

	public EntityImpl() throws Exception
	{
		knowledgeCx = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		sqlDelete   = Outside.service(this, "gus.y.knowledgedb1.sql.delete");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size() < 3) throw new Exception("k-remove-link: usage: k-remove-link <table> <id1> <id2>");
		String table = (String) list.get(0);
		String id1   = (String) list.get(1);
		String id2   = (String) list.get(2);
		String sql   = "DELETE FROM " + table + "_link WHERE ID_LINKER = " + id1 + " AND ID_LINKED = " + id2;
		Connection cx = (Connection) knowledgeCx.g();
		return sqlDelete.t(new Object[]{cx, sql});
	}
}