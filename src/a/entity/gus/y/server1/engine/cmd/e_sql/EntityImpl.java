package a.entity.gus.y.server1.engine.cmd.e_sql;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260412";}

	private Service entitydbCx;
	private Service sqlSelect;
	private Service sqlInsert;
	private Service sqlUpdate;
	private Service sqlDelete;

	public EntityImpl() throws Exception
	{
		entitydbCx = Outside.service(this, "gus.y.entitydb1.cx.main");
		sqlSelect = Outside.service(this, "gus.y.knowledgedb1.sql.select");
		sqlInsert = Outside.service(this, "gus.y.knowledgedb1.sql.insert");
		sqlUpdate = Outside.service(this, "gus.y.knowledgedb1.sql.update");
		sqlDelete = Outside.service(this, "gus.y.knowledgedb1.sql.delete");
	}

	private Object help()
	{
		return "e-sql <sql> — SQL brut sur entitydb1 (SHOW, SELECT, INSERT, UPDATE, DELETE)\n"
			 + "e-sql help — cette aide";
	}

	public Object t(Object obj) throws Exception
	{
		List args = (List) obj;
		String sql = String.join(" ", args).trim();
		String type = sql.toLowerCase();
		Connection cx = (Connection) entitydbCx.g();
		Object[] params = new Object[]{cx, sql};

		if(type.equals("help")) return help();
		if(type.startsWith("show")) return sqlSelect.t(params);
		if(type.startsWith("select")) return sqlSelect.t(params);
		if(type.startsWith("insert")) return sqlInsert.t(params);
		if(type.startsWith("update")) return sqlUpdate.t(params);
		if(type.startsWith("delete")) return sqlDelete.t(params);

		throw new Exception("e-sql: type SQL non supporté: " + sql);
	}
}
