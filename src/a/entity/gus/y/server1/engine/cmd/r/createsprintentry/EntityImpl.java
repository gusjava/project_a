package a.entity.gus.y.server1.engine.cmd.r.createsprintentry;

import java.sql.*;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service roadmapCx;
	private Service sqlInsert;

	public EntityImpl() throws Exception
	{
		roadmapCx = Outside.service(this, "gus.y.roadmapdb1.cx.main");
		sqlInsert  = Outside.service(this, "gus.y.roadmapdb1.sql.insert");
	}

	public Object t(Object obj) throws Exception
	{
		if(!(obj instanceof Map)) throw new Exception("r-createsprintentry: JSON manquant (utiliser :<json>)");
		Map fields = (Map) obj;
		Connection cx = (Connection) roadmapCx.g();
		return sqlInsert.t(new Object[]{cx, buildInsert("sprint_entry", fields)});
	}

	private String buildInsert(String table, Map fields)
	{
		StringBuffer cols = new StringBuffer("date_created");
		StringBuffer vals = new StringBuffer("NOW()");
		Iterator it = fields.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			cols.append("," + key);
			vals.append("," + sqlValue(fields.get(key)));
		}
		return "INSERT INTO " + table + " (" + cols + ") VALUES (" + vals + ")";
	}

	private String sqlValue(Object val)
	{
		if(val == null) return "NULL";
		if(val instanceof Number) return val.toString();
		if(val instanceof Boolean) return ((Boolean) val) ? "1" : "0";
		return "'" + val.toString().replace("'", "''") + "'";
	}
}