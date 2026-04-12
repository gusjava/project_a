package a.entity.gus.y.server1.engine.cmd.r;

import java.sql.*;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260411";}

	private Service roadmapCx;
	private Service sqlSelect;
	private Service sqlInsert;
	private Service sqlDelete;
	private Service sqlUpdate;
	private Service jsonParser;

	public EntityImpl() throws Exception
	{
		roadmapCx  = Outside.service(this, "gus.y.roadmapdb1.cx.main");
		sqlSelect  = Outside.service(this, "gus.y.roadmapdb1.sql.select");
		sqlInsert  = Outside.service(this, "gus.y.roadmapdb1.sql.insert");
		sqlDelete  = Outside.service(this, "gus.y.roadmapdb1.sql.delete");
		sqlUpdate  = Outside.service(this, "gus.y.roadmapdb1.sql.update");
		jsonParser = Outside.service(this, "gus06.file.convert.json.parser");
	}

	public Object t(Object obj) throws Exception
	{
		List args = (List) obj;
		if(args.isEmpty()) throw new Exception("r: commande manquante");

		String cmd = ((String) args.get(0)).toLowerCase();

		if(cmd.equals("show"))             return show(args);
		if(cmd.equals("count"))            return count(args);
		if(cmd.equals("tables"))           return tables();
		if(cmd.equals("help"))             return help();
		if(cmd.equals("tags"))             return tags();
		
		if(cmd.equals("create-objective"))  return createObjective(args);
		if(cmd.equals("create-task"))       return createTask(args);
		if(cmd.equals("create-note"))       return createGeneric(args, "note");
		if(cmd.equals("create-sprint"))     return createGeneric(args, "sprint");
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

		if(cmd.equals("get"))        return get(args);
		if(cmd.equals("list"))       return list(args);
		if(cmd.equals("search"))     return search(args);

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

	private Object show(List args) throws Exception
	{
		if(args.size() < 2) throw new Exception("r show: nom de table manquant");
		String table = (String) args.get(1);
		String sql = "SHOW COLUMNS FROM " + table;
		Connection cx = (Connection) roadmapCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
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

	private Object count(List args) throws Exception
	{
		if(args.size() < 2) throw new Exception("r count: nom de table manquant");
		String table = (String) args.get(1);
		Connection cx = (Connection) roadmapCx.g();
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM " + table);
		Object result = rs.next() ? rs.getObject(1) : null;
		st.close();
		return result;
	}

	private Object createObjective(List args) throws Exception
	{
		if(args.size() < 2) throw new Exception("r create-objective: JSON manquant");
		StringBuffer sb = new StringBuffer();
		for(int i = 1; i < args.size(); i++)
		{
			if(i > 1) sb.append(" ");
			sb.append((String) args.get(i));
		}
		Map fields = (Map) jsonParser.t(sb.toString());
		String sql = buildInsert("objective", fields);
		Connection cx = (Connection) roadmapCx.g();
		return sqlInsert.t(new Object[]{cx, sql});
	}

	private Object createGeneric(List args, String table) throws Exception
	{
		String cmdName = "r create-" + table.replace("_", "-");
		if(args.size() < 2) throw new Exception(cmdName + ": JSON manquant");
		StringBuffer sb = new StringBuffer();
		for(int i = 1; i < args.size(); i++)
		{
			if(i > 1) sb.append(" ");
			sb.append((String) args.get(i));
		}
		Map fields = (Map) jsonParser.t(sb.toString());
		String sql = buildInsert(table, fields);
		Connection cx = (Connection) roadmapCx.g();
		return sqlInsert.t(new Object[]{cx, sql});
	}

	private Object createTask(List args) throws Exception
	{
		if(args.size() < 2) throw new Exception("r create-task: JSON manquant");
		StringBuffer sb = new StringBuffer();
		for(int i = 1; i < args.size(); i++)
		{
			if(i > 1) sb.append(" ");
			sb.append((String) args.get(i));
		}
		Map fields = (Map) jsonParser.t(sb.toString());
		String sql = buildInsert("task", fields);
		Connection cx = (Connection) roadmapCx.g();
		return sqlInsert.t(new Object[]{cx, sql});
	}

	private Object updateGeneric(List args, String table) throws Exception
	{
		String cmdName = "r update-" + table.replace("_", "-");
		if(args.size() < 2) throw new Exception(cmdName + ": JSON manquant");
		StringBuffer sb = new StringBuffer();
		for(int i = 1; i < args.size(); i++)
		{
			if(i > 1) sb.append(" ");
			sb.append((String) args.get(i));
		}
		Map fields = (Map) jsonParser.t(sb.toString());
		Object id = fields.remove("id");
		if(id == null) throw new Exception(cmdName + ": champ 'id' manquant dans le JSON");
		String sql = buildUpdate(table, fields, id);
		Connection cx = (Connection) roadmapCx.g();
		return sqlUpdate.t(new Object[]{cx, sql});
	}

	private Object deleteGeneric(List args, String table) throws Exception
	{
		String cmdName = "r delete-" + table.replace("_", "-");
		if(args.size() < 2) throw new Exception(cmdName + ": id manquant");
		String id = (String) args.get(1);
		String sql = "DELETE FROM " + table + " WHERE id = " + id;
		Connection cx = (Connection) roadmapCx.g();
		return sqlDelete.t(new Object[]{cx, sql});
	}

	private Object get(List args) throws Exception
	{
		if(args.size() < 3) throw new Exception("r get: usage: get <table> <id>");
		String table = (String) args.get(1);
		String id    = (String) args.get(2);
		String sql   = "SELECT * FROM " + table + " WHERE id = " + id;
		Connection cx = (Connection) roadmapCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
	}

	private Object list(List args) throws Exception
	{
		if(args.size() < 2) throw new Exception("r list: usage: list <table> [limit]");
		String table = (String) args.get(1);
		int limit    = args.size() >= 3 ? Integer.parseInt((String) args.get(2)) : 20;
		String sql   = "SELECT * FROM " + table + " ORDER BY date_created DESC LIMIT " + limit;
		Connection cx = (Connection) roadmapCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
	}

	private Object search(List args) throws Exception
	{
		if(args.size() < 4) throw new Exception("r search: usage: search <table> <field> <value>");
		String table = (String) args.get(1);
		String field = (String) args.get(2);
		String value = (String) args.get(3);
		String sql   = "SELECT * FROM " + table + " WHERE " + field + " LIKE '%" + value.replace("'", "''") + "%'";
		Connection cx = (Connection) roadmapCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
	}

	private Object tagsOf(List args) throws Exception
	{
		if(args.size() < 3) throw new Exception("r tags-of: usage: tags-of <table> <id>");
		String table  = (String) args.get(1);
		String id     = (String) args.get(2);
		String fk     = "ID_" + table.toUpperCase();
		String sql    = "SELECT TAG FROM " + table + "_tag WHERE " + fk + " = " + id;
		Connection cx = (Connection) roadmapCx.g();
		Statement st  = cx.createStatement();
		ResultSet rs  = st.executeQuery(sql);
		List result   = new ArrayList();
		while(rs.next()) result.add(rs.getString(1));
		st.close();
		return result;
	}

	private Object addTag(List args) throws Exception
	{
		if(args.size() < 4) throw new Exception("r add-tag: usage: add-tag <table> <id> <tag>");
		String table  = (String) args.get(1);
		String id     = (String) args.get(2);
		String tag    = (String) args.get(3);
		String fk     = "ID_" + table.toUpperCase();
		String sql    = "INSERT INTO " + table + "_tag (" + fk + ", TAG) VALUES (" + id + ", '" + tag.replace("'", "''") + "')";
		Connection cx = (Connection) roadmapCx.g();
		return sqlInsert.t(new Object[]{cx, sql});
	}

	private Object removeTag(List args) throws Exception
	{
		if(args.size() < 4) throw new Exception("r remove-tag: usage: remove-tag <table> <id> <tag>");
		String table  = (String) args.get(1);
		String id     = (String) args.get(2);
		String tag    = (String) args.get(3);
		String fk     = "ID_" + table.toUpperCase();
		String sql    = "DELETE FROM " + table + "_tag WHERE " + fk + " = " + id + " AND TAG = '" + tag.replace("'", "''") + "'";
		Connection cx = (Connection) roadmapCx.g();
		return sqlDelete.t(new Object[]{cx, sql});
	}

	private Object detailOf(List args, String table, boolean hasTags) throws Exception
	{
		if(args.size() < 2) throw new Exception("r detail-of-" + table.replace("_", "-") + ": id manquant");
		String id = (String) args.get(1);
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
		if(fields.isEmpty()) throw new Exception("r update-" + table.replace("_", "-") + ": aucun champ à mettre à jour");
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
		return "r show <table>                — colonnes d'une table\n"
			 + "r count <table>               — nombre de lignes\n"
			 + "r tables                      — liste des tables\n"
			 + "r get <table> <id>            — un enregistrement par id\n"
			 + "r list <table> [limit]        — derniers enregistrements (défaut 20)\n"
			 + "r search <table> <field> <value> — recherche LIKE sur un champ\n"
			 + "r create-objective <json>     — insère dans objective\n"
			 + "r create-task <json>          — insère dans task\n"
			 + "r create-note <json>          — insère dans note\n"
			 + "r create-sprint <json>        — insère dans sprint\n"
			 + "r create-sprint-entry <json>  — insère dans sprint_entry\n"
			 + "r update-objective <json>     — met à jour dans objective (id requis)\n"
			 + "r update-task <json>          — met à jour dans task (id requis)\n"
			 + "r update-note <json>          — met à jour dans note (id requis)\n"
			 + "r update-sprint <json>        — met à jour dans sprint (id requis)\n"
			 + "r update-sprint-entry <json>  — met à jour dans sprint_entry (id requis)\n"
			 + "r delete-objective <id>       — supprime dans objective\n"
			 + "r delete-task <id>            — supprime dans task\n"
			 + "r delete-note <id>            — supprime dans note\n"
			 + "r delete-sprint <id>          — supprime dans sprint\n"
			 + "r delete-sprint-entry <id>    — supprime dans sprint_entry\n"
			 + "r detail-of-note <id>         — détail complet (data+tags)\n"
			 + "r detail-of-objective <id>    — détail complet (data+tags)\n"
			 + "r detail-of-task <id>         — détail complet (data+tags)\n"
			 + "r detail-of-sprint <id>       — détail complet (data)\n"
			 + "r detail-of-sprint-entry <id> — détail complet (data)\n"
			 + "r tags                        — tous les tags distincts (*_tag)\n"
			 + "r tags-of <table> <id>        — tags d'un enregistrement\n"
			 + "r add-tag <table> <id> <tag>  — ajouter un tag\n"
			 + "r remove-tag <table> <id> <tag> — supprimer un tag\n"
			 + "r help                        — cette aide";
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
}