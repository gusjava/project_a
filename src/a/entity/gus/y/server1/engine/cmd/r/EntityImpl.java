package a.entity.gus.y.server1.engine.cmd.r;

import java.sql.*;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260414";}

	private Service roadmapCx;
	private Service sqlSelect;
	private Service sqlInsert;
	private Service sqlDelete;
	private Service sqlUpdate;
	private Service cmdRSql;

	public EntityImpl() throws Exception
	{
		roadmapCx = Outside.service(this, "gus.y.roadmapdb1.cx.main");
		sqlSelect  = Outside.service(this, "gus.y.roadmapdb1.sql.select");
		sqlInsert  = Outside.service(this, "gus.y.roadmapdb1.sql.insert");
		sqlDelete  = Outside.service(this, "gus.y.roadmapdb1.sql.delete");
		sqlUpdate  = Outside.service(this, "gus.y.roadmapdb1.sql.update");
		cmdRSql    = Outside.service(this, "gus.y.server1.engine.cmd.r.sql");
	}

	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		List cmds = (List) map.get("cmds");
		Object args = map.get("args");

		if(cmds.size() < 2) throw new Exception("r: commande manquante");
		String cmd = joinCmds(cmds, 1).toLowerCase();

		if(cmd.equals("show"))   return show(args);
		if(cmd.equals("count"))  return count(args);
		if(cmd.equals("tables")) return tables();
		if(cmd.equals("help"))   return help();
		if(cmd.equals("tags"))   return tags();
		if(cmd.equals("sql"))    return cmdRSql.t(args);

		if(cmd.equals("create-objective"))    return createGeneric(args, "objective");
		if(cmd.equals("create-task"))         return createGeneric(args, "task");
		if(cmd.equals("create-note"))         return createGeneric(args, "note");
		if(cmd.equals("create-sprint"))       return createGeneric(args, "sprint");
		if(cmd.equals("create-sprint-entry")) return createGeneric(args, "sprint_entry");

		if(cmd.equals("update-objective"))    return updateGeneric(args, "objective");
		if(cmd.equals("update-task"))         return updateGeneric(args, "task");
		if(cmd.equals("update-note"))         return updateGeneric(args, "note");
		if(cmd.equals("update-sprint"))       return updateGeneric(args, "sprint");
		if(cmd.equals("update-sprint-entry")) return updateGeneric(args, "sprint_entry");

		if(cmd.equals("delete-objective"))    return deleteGeneric(args, "objective");
		if(cmd.equals("delete-task"))         return deleteGeneric(args, "task");
		if(cmd.equals("delete-note"))         return deleteGeneric(args, "note");
		if(cmd.equals("delete-sprint"))       return deleteGeneric(args, "sprint");
		if(cmd.equals("delete-sprint-entry")) return deleteGeneric(args, "sprint_entry");

		if(cmd.equals("get"))    return get(args);
		if(cmd.equals("list"))   return list(args);
		if(cmd.equals("search")) return search(args);

		if(cmd.equals("tags-of"))    return tagsOf(args);
		if(cmd.equals("add-tag"))    return addTag(args);
		if(cmd.equals("remove-tag")) return removeTag(args);

		if(cmd.equals("detail-of-note"))         return detailOf(args, "note",         true);
		if(cmd.equals("detail-of-objective"))    return detailOf(args, "objective",    true);
		if(cmd.equals("detail-of-task"))         return detailOf(args, "task",         true);
		if(cmd.equals("detail-of-sprint"))       return detailOf(args, "sprint",       false);
		if(cmd.equals("detail-of-sprint-entry")) return detailOf(args, "sprint_entry", false);

		throw new Exception("r: commande inconnue: " + cmd);
	}

	private Object show(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("r-show: nom de table manquant");
		String table = (String) list.get(0);
		Connection cx = (Connection) roadmapCx.g();
		return sqlSelect.t(new Object[]{cx, "SHOW COLUMNS FROM " + table});
	}

	private Object tables() throws Exception
	{
		Connection cx = (Connection) roadmapCx.g();
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery("SHOW TABLES");
		List result = new ArrayList();
		while(rs.next()) result.add(rs.getString(1));
		st.close();
		return result;
	}

	private Object count(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("r-count: nom de table manquant");
		String table = (String) list.get(0);
		Connection cx = (Connection) roadmapCx.g();
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM " + table);
		Object result = rs.next() ? rs.getObject(1) : null;
		st.close();
		return result;
	}

	private Object createGeneric(Object args, String table) throws Exception
	{
		String cmdName = "r-create-" + table.replace("_", "-");
		if(!(args instanceof Map)) throw new Exception(cmdName + ": JSON manquant (utiliser :<json>)");
		Map fields = (Map) args;
		String sql = buildInsert(table, fields);
		Connection cx = (Connection) roadmapCx.g();
		return sqlInsert.t(new Object[]{cx, sql});
	}

	private Object updateGeneric(Object args, String table) throws Exception
	{
		String cmdName = "r-update-" + table.replace("_", "-");
		if(!(args instanceof Map)) throw new Exception(cmdName + ": JSON manquant (utiliser :<json>)");
		Map fields = new HashMap((Map) args);
		Object id = fields.remove("id");
		if(id == null) throw new Exception(cmdName + ": champ 'id' manquant dans le JSON");
		String sql = buildUpdate(table, fields, id);
		Connection cx = (Connection) roadmapCx.g();
		return sqlUpdate.t(new Object[]{cx, sql});
	}

	private Object deleteGeneric(Object args, String table) throws Exception
	{
		List list = (List) args;
		String cmdName = "r-delete-" + table.replace("_", "-");
		if(list == null || list.isEmpty()) throw new Exception(cmdName + ": id manquant");
		String id = (String) list.get(0);
		String sql = "DELETE FROM " + table + " WHERE id = " + id;
		Connection cx = (Connection) roadmapCx.g();
		return sqlDelete.t(new Object[]{cx, sql});
	}

	private Object get(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 2) throw new Exception("r-get: usage: r-get <table> <id>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		String sql   = "SELECT * FROM " + table + " WHERE id = " + id;
		Connection cx = (Connection) roadmapCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
	}

	private Object list(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("r-list: usage: r-list <table> [limit]");
		String table = (String) list.get(0);
		int limit    = list.size() >= 2 ? Integer.parseInt((String) list.get(1)) : 20;
		String sql   = "SELECT * FROM " + table + " ORDER BY date_created DESC LIMIT " + limit;
		Connection cx = (Connection) roadmapCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
	}

	private Object search(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 3) throw new Exception("r-search: usage: r-search <table> <field> <value>");
		String table = (String) list.get(0);
		String field = (String) list.get(1);
		String value = (String) list.get(2);
		String sql   = "SELECT * FROM " + table + " WHERE " + field + " LIKE '%" + value.replace("'", "''") + "%'";
		Connection cx = (Connection) roadmapCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
	}

	private Object tagsOf(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 2) throw new Exception("r-tags-of: usage: r-tags-of <table> <id>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		String fk    = "ID_" + table.toUpperCase();
		String sql   = "SELECT TAG FROM " + table + "_tag WHERE " + fk + " = " + id;
		Connection cx = (Connection) roadmapCx.g();
		Statement st  = cx.createStatement();
		ResultSet rs  = st.executeQuery(sql);
		List result   = new ArrayList();
		while(rs.next()) result.add(rs.getString(1));
		st.close();
		return result;
	}

	private Object addTag(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 3) throw new Exception("r-add-tag: usage: r-add-tag <table> <id> <tag>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		String tag   = (String) list.get(2);
		String fk    = "ID_" + table.toUpperCase();
		String sql   = "INSERT INTO " + table + "_tag (" + fk + ", TAG) VALUES (" + id + ", '" + tag.replace("'", "''") + "')";
		Connection cx = (Connection) roadmapCx.g();
		return sqlInsert.t(new Object[]{cx, sql});
	}

	private Object removeTag(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 3) throw new Exception("r-remove-tag: usage: r-remove-tag <table> <id> <tag>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		String tag   = (String) list.get(2);
		String fk    = "ID_" + table.toUpperCase();
		String sql   = "DELETE FROM " + table + "_tag WHERE " + fk + " = " + id + " AND TAG = '" + tag.replace("'", "''") + "'";
		Connection cx = (Connection) roadmapCx.g();
		return sqlDelete.t(new Object[]{cx, sql});
	}

	private Object detailOf(Object args, String table, boolean hasTags) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("r-detail-of-" + table.replace("_", "-") + ": id manquant");
		String id = (String) list.get(0);
		Connection cx = (Connection) roadmapCx.g();
		Map result = new LinkedHashMap();

		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM " + table + " WHERE id = " + id);
		Map data = new LinkedHashMap();
		if(rs.next())
		{
			ResultSetMetaData meta = rs.getMetaData();
			for(int i = 1; i <= meta.getColumnCount(); i++)
				data.put(meta.getColumnName(i).toLowerCase(), rs.getObject(i));
		}
		st.close();
		result.put("data", data);

		if(hasTags)
		{
			String fk = "ID_" + table.toUpperCase();
			Statement st2 = cx.createStatement();
			ResultSet rs2 = st2.executeQuery("SELECT TAG FROM " + table + "_tag WHERE " + fk + " = " + id);
			List tags = new ArrayList();
			while(rs2.next()) tags.add(rs2.getString(1));
			rs2.close();
			st2.close();
			result.put("tags", tags);
		}

		return result;
	}

	private String buildUpdate(String table, Map fields, Object id) throws Exception
	{
		if(fields.isEmpty()) throw new Exception("r-update-" + table.replace("_", "-") + ": aucun champ \u00e0 mettre \u00e0 jour");
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

	private String buildInsert(String table, Map fields) throws Exception
	{
		StringBuffer cols = new StringBuffer("date_created");
		StringBuffer vals = new StringBuffer("NOW()");
		Iterator it = fields.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Object val = fields.get(key);
			cols.append("," + key);
			vals.append("," + sqlValue(val));
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

	private Object help()
	{
		return "r-show <table>                   \u2014 colonnes d'une table\n"
			 + "r-count <table>                  \u2014 nombre de lignes\n"
			 + "r-tables                         \u2014 liste des tables\n"
			 + "r-get <table> <id>               \u2014 un enregistrement par id\n"
			 + "r-list <table> [limit]           \u2014 derniers enregistrements (d\u00e9faut 20)\n"
			 + "r-search <table> <field> <value> \u2014 recherche LIKE sur un champ\n"
			 + "r-sql <sql>                      \u2014 SQL brut sur roadmapdb1\n"
			 + "r-create-objective :<json>       \u2014 ins\u00e8re dans objective\n"
			 + "r-create-task :<json>            \u2014 ins\u00e8re dans task\n"
			 + "r-create-note :<json>            \u2014 ins\u00e8re dans note\n"
			 + "r-create-sprint :<json>          \u2014 ins\u00e8re dans sprint\n"
			 + "r-create-sprint-entry :<json>    \u2014 ins\u00e8re dans sprint_entry\n"
			 + "r-update-objective :<json>       \u2014 met \u00e0 jour dans objective (id requis)\n"
			 + "r-update-task :<json>            \u2014 met \u00e0 jour dans task (id requis)\n"
			 + "r-update-note :<json>            \u2014 met \u00e0 jour dans note (id requis)\n"
			 + "r-update-sprint :<json>          \u2014 met \u00e0 jour dans sprint (id requis)\n"
			 + "r-update-sprint-entry :<json>    \u2014 met \u00e0 jour dans sprint_entry (id requis)\n"
			 + "r-delete-objective <id>          \u2014 supprime dans objective\n"
			 + "r-delete-task <id>               \u2014 supprime dans task\n"
			 + "r-delete-note <id>               \u2014 supprime dans note\n"
			 + "r-delete-sprint <id>             \u2014 supprime dans sprint\n"
			 + "r-delete-sprint-entry <id>       \u2014 supprime dans sprint_entry\n"
			 + "r-detail-of-note <id>            \u2014 d\u00e9tail complet (data+tags)\n"
			 + "r-detail-of-objective <id>       \u2014 d\u00e9tail complet (data+tags)\n"
			 + "r-detail-of-task <id>            \u2014 d\u00e9tail complet (data+tags)\n"
			 + "r-detail-of-sprint <id>          \u2014 d\u00e9tail complet (data)\n"
			 + "r-detail-of-sprint-entry <id>    \u2014 d\u00e9tail complet (data)\n"
			 + "r-tags                           \u2014 tous les tags distincts (*_tag)\n"
			 + "r-tags-of <table> <id>           \u2014 tags d'un enregistrement\n"
			 + "r-add-tag <table> <id> <tag>     \u2014 ajouter un tag\n"
			 + "r-remove-tag <table> <id> <tag>  \u2014 supprimer un tag\n"
			 + "r-help                           \u2014 cette aide";
	}

	private static final String[] TAG_TABLES = {
		"note_tag", "objective_tag", "task_tag"
	};

	private Object tags() throws Exception
	{
		Connection cx = (Connection) roadmapCx.g();
		Set tags = new TreeSet();
		for(int i = 0; i < TAG_TABLES.length; i++)
		{
			Statement st = cx.createStatement();
			ResultSet r = st.executeQuery("SELECT DISTINCT TAG FROM " + TAG_TABLES[i]);
			while(r.next()) tags.add(r.getString(1));
			r.close();
			st.close();
		}
		return new ArrayList(tags);
	}

	private static String joinCmds(List cmds, int from)
	{
		StringBuffer sb = new StringBuffer();
		for(int i = from; i < cmds.size(); i++) {
			if(i > from) sb.append("-");
			sb.append(cmds.get(i));
		}
		return sb.toString();
	}
}
