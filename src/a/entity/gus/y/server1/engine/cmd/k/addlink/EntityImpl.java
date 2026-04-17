package a.entity.gus.y.server1.engine.cmd.k.addlink;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service knowledgeCx;
	private Service sqlInsert;

	public EntityImpl() throws Exception
	{
		knowledgeCx = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		sqlInsert   = Outside.service(this, "gus.y.knowledgedb1.sql.insert");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size() < 3) throw new Exception("k-add-link: usage: k-add-link <table> <id1> <id2> [type]");
		String table = (String) list.get(0);
		String id1   = (String) list.get(1);
		String id2   = (String) list.get(2);
		String type  = list.size() >= 4 ? (String) list.get(3) : "";
		String sql   = "INSERT INTO " + table + "_link (ID_LINKER, ID_LINKED, TYPE) VALUES (" + id1 + ", " + id2 + ", '" + type.replace("'", "''") + "')";
		Connection cx = (Connection) knowledgeCx.g();
		return sqlInsert.t(new Object[]{cx, sql});
	}
}