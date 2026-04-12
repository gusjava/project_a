package a.entity.gus.y.server1.engine.cmd.k_sql;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260411";}

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

	private Object help()
	{
		return "k-sql <sql> — SQL brut sur knowledgedb1 (SHOW, SELECT, INSERT, UPDATE, DELETE)\n"
			 + "k-sql help  — cette aide";
	}

	public Object t(Object obj) throws Exception
	{
		List args = (List) obj;
		String sql = String.join(" ", args).trim();
		String type = sql.toLowerCase();

		if(type.equals("help")) return help();

		Connection cx = (Connection) knowledgeCx.g();
		Object[] params = new Object[]{cx, sql};

		if(type.startsWith("show"))   return sqlSelect.t(params);
		if(type.startsWith("select")) return sqlSelect.t(params);
		if(type.startsWith("insert")) return sqlInsert.t(params);
		if(type.startsWith("update")) return sqlUpdate.t(params);
		if(type.startsWith("delete")) return sqlDelete.t(params);

		throw new Exception("k-sql: type SQL non supporté: " + sql);
	}
}
