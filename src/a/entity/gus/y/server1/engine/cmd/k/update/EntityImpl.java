package a.entity.gus.y.server1.engine.cmd.k.update;

import java.sql.Connection;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service knowledgeCx;
	private Service sqlUpdate;

	public EntityImpl() throws Exception
	{
		knowledgeCx = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		sqlUpdate   = Outside.service(this, "gus.y.knowledgedb1.sql.update");
	}

	public Object t(Object obj) throws Exception
	{
		Map args = new HashMap((Map) obj);
		String table = (String) args.remove("table");
		String cmdName = "k-update-" + table.replace("_", "-");
		if(args.isEmpty()) throw new Exception(cmdName + ": JSON manquant (utiliser :<json>)");
		Object id = args.remove("id");
		if(id == null) throw new Exception(cmdName + ": champ 'id' manquant dans le JSON");
		String sql = buildUpdate(table, args, id, cmdName);
		Connection cx = (Connection) knowledgeCx.g();
		return sqlUpdate.t(new Object[]{cx, sql});
	}

	private String buildUpdate(String table, Map fields, Object id, String cmdName) throws Exception
	{
		if(fields.isEmpty()) throw new Exception(cmdName + ": aucun champ \u00e0 mettre \u00e0 jour");
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
