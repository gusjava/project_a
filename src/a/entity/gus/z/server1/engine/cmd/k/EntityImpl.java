package a.entity.gus.z.server1.engine.cmd.k;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service knowledgeCx;
	private Service sqlSelect;
	private Service sqlInsert;
	private Service sqlUpdate;
	private Service sqlDelete;

	public EntityImpl() throws Exception
	{
		knowledgeCx = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		sqlSelect = Outside.service(this, "gus.y.knowledgedb1.sql.select");
		sqlInsert = Outside.service(this, "gus.y.knowledgedb1.sql.insert");
		sqlUpdate = Outside.service(this, "gus.y.knowledgedb1.sql.update");
		sqlDelete = Outside.service(this, "gus.y.knowledgedb1.sql.delete");
	}

	public Object t(Object obj) throws Exception
	{
		List args = (List) obj;
		String sql = String.join(" ", args).trim();
		String type = sql.toLowerCase();
		Connection cx = (Connection) knowledgeCx.g();
		Object[] params = new Object[]{cx, sql};
		if(type.startsWith("select")) return sqlSelect.t(params);
		if(type.startsWith("insert")) return sqlInsert.t(params);
		if(type.startsWith("update")) return sqlUpdate.t(params);
		if(type.startsWith("delete")) return sqlDelete.t(params);
		throw new Exception("Unsupported SQL type: " + sql);
	}
}
