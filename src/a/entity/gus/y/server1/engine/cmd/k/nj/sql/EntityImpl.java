package a.entity.gus.y.server1.engine.cmd.k.nj.sql;

import java.sql.Connection;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service engine;
	private Service sqlSelect;
	private Service sqlInsert;
	private Service sqlUpdate;
	private Service sqlDelete;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
		sqlSelect       = Outside.service(this, "gus.y.knowledgedb1.sql.select");
		sqlInsert       = Outside.service(this, "gus.y.knowledgedb1.sql.insert");
		sqlUpdate       = Outside.service(this, "gus.y.knowledgedb1.sql.update");
		sqlDelete       = Outside.service(this, "gus.y.knowledgedb1.sql.delete");
	}

	public Object t(Object obj) throws Exception
	{
		String sql  = toSql(obj);
		String sql_ = sql.toLowerCase();

		Object[] params  = new Object[]{cx(), sql};

		if(sql_.startsWith("show"))   return sqlSelect.t(params);
		if(sql_.startsWith("select")) return sqlSelect.t(params);
		if(sql_.startsWith("insert")) return sqlInsert.t(params);
		if(sql_.startsWith("update")) return sqlUpdate.t(params);
		if(sql_.startsWith("delete")) return sqlDelete.t(params);
		if(sql_.startsWith("alter"))  return sqlUpdate.t(params);
		if(sql_.startsWith("create")) return sqlUpdate.t(params);
		if(sql_.startsWith("drop"))   return sqlUpdate.t(params);

		throw new Exception("Unsupported SQL: " + sql);
	}
	
	private String toSql(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof Map) return (String) ((Map) obj).get("sql");
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}
