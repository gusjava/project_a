package a.entity.gus.y.server1.engine.cmd.r.updateobjective;

import java.sql.*;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service roadmapCx;
	private Service sqlUpdate;

	public EntityImpl() throws Exception
	{
		roadmapCx = Outside.service(this, "gus.y.roadmapdb1.cx.main");
		sqlUpdate  = Outside.service(this, "gus.y.roadmapdb1.sql.update");
	}

	public Object t(Object obj) throws Exception
	{
		if(!(obj instanceof Map)) throw new Exception("r-updateobjective: JSON manquant (utiliser :<json>)");
		Map fields = new HashMap((Map) obj);
		Object id = fields.remove("id");
		if(id == null) throw new Exception("r-updateobjective: champ 'id' manquant dans le JSON");
		Connection cx = (Connection) roadmapCx.g();
		return sqlUpdate.t(new Object[]{cx, buildUpdate("objective", fields, id)});
	}

	private String buildUpdate(String table, Map fields, Object id) throws Exception
	{
		if(fields.isEmpty()) throw new Exception("r-updateobjective: aucun champ \u00e0 mettre \u00e0 jour");
		StringBuffer set = new StringBuffer();
		Iterator it = fields.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(set.length() > 0) set.append(", ");
			set.append(key + " = " + sqlValue(fields.get(key)));
		}
		return "UPDATE " + table + " SET " + set + " WHERE id = " + id;
	}

	private String sqlValue(Object val)
	{
		if(val == null) return "NULL";
		if(val instanceof Number) return val.toString();
		if(val instanceof Boolean) return ((Boolean) val) ? "1" : "0";
		return "'" + val.toString().replace("'", "''") + "'";
	}
}