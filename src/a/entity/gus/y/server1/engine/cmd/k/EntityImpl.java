package a.entity.gus.y.server1.engine.cmd.k;

import java.sql.*;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260414";}

	private Service knowledgeCx;
	private Service sqlSelect;
	private Service sqlInsert;
	private Service sqlDelete;
	private Service sqlUpdate;
	private Service cmdKSql;

	public EntityImpl() throws Exception
	{
		knowledgeCx = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		sqlSelect   = Outside.service(this, "gus.y.knowledgedb1.sql.select");
		sqlInsert   = Outside.service(this, "gus.y.knowledgedb1.sql.insert");
		sqlDelete   = Outside.service(this, "gus.y.knowledgedb1.sql.delete");
		sqlUpdate   = Outside.service(this, "gus.y.knowledgedb1.sql.update");
		cmdKSql     = Outside.service(this, "gus.y.server1.engine.cmd.k.sql");
	}

	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		List cmds = (List) map.get("cmds");
		Object args = map.get("args");

		if(cmds.size() < 2) throw new Exception("k: commande manquante");
		String cmd = joinCmds(cmds, 1).toLowerCase();

		if(cmd.equals("show"))   return show(args);
		if(cmd.equals("count"))  return count(args);
		if(cmd.equals("tables")) return tables();
		if(cmd.equals("help"))   return help();
		if(cmd.equals("tags"))   return tags();
		if(cmd.equals("sql"))    return cmdKSql.t(args);

		if(cmd.equals("create-knowledge"))          return createGeneric(args, "knowledge");
		if(cmd.equals("create-todo"))               return createGeneric(args, "todo");
		if(cmd.equals("create-doc-x"))              return createGeneric(args, "doc_x");
		if(cmd.equals("create-doc-y"))              return createGeneric(args, "doc_y");
		if(cmd.equals("create-doc-z"))              return createGeneric(args, "doc_z");
		if(cmd.equals("create-knowledge-feedback")) return createGeneric(args, "knowledge_feedback");

		if(cmd.equals("update-knowledge"))          return updateGeneric(args, "knowledge");
		if(cmd.equals("update-todo"))               return updateGeneric(args, "todo");
		if(cmd.equals("update-doc-x"))              return updateGeneric(args, "doc_x");
		if(cmd.equals("update-doc-y"))              return updateGeneric(args, "doc_y");
		if(cmd.equals("update-doc-z"))              return updateGeneric(args, "doc_z");
		if(cmd.equals("update-knowledge-feedback")) return updateGeneric(args, "knowledge_feedback");

		if(cmd.equals("delete-knowledge"))          return deleteGeneric(args, "knowledge");
		if(cmd.equals("delete-todo"))               return deleteGeneric(args, "todo");
		if(cmd.equals("delete-doc-x"))              return deleteGeneric(args, "doc_x");
		if(cmd.equals("delete-doc-y"))              return deleteGeneric(args, "doc_y");
		if(cmd.equals("delete-doc-z"))              return deleteGeneric(args, "doc_z");
		if(cmd.equals("delete-knowledge-feedback")) return deleteGeneric(args, "knowledge_feedback");

		if(cmd.equals("get"))    return get(args);
		if(cmd.equals("list"))   return list(args);
		if(cmd.equals("search")) return search(args);

		if(cmd.equals("tags-of"))    return tagsOf(args);
		if(cmd.equals("add-tag"))    return addTag(args);
		if(cmd.equals("remove-tag")) return removeTag(args);

		if(cmd.equals("links-of"))    return linksOf(args);
		if(cmd.equals("add-link"))    return addLink(args);
		if(cmd.equals("remove-link")) return removeLink(args);

		if(cmd.equals("add-todo-knowledge"))    return addTodoKnowledge(args);
		if(cmd.equals("remove-todo-knowledge")) return removeTodoKnowledge(args);

		if(cmd.equals("detail-of-knowledge"))          return detailOf(args, "knowledge",          true,  true);
		if(cmd.equals("detail-of-todo"))               return detailOf(args, "todo",               true,  true);
		if(cmd.equals("detail-of-doc-x"))              return detailOf(args, "doc_x",              true,  false);
		if(cmd.equals("detail-of-doc-y"))              return detailOf(args, "doc_y",              true,  false);
		if(cmd.equals("detail-of-doc-z"))              return detailOf(args, "doc_z",              true,  false);
		if(cmd.equals("detail-of-knowledge-feedback")) return detailOf(args, "knowledge_feedback", false, false);

		throw new Exception("k: commande inconnue: " + cmd);
	}

	private Object show(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("k-show: nom de table manquant");
		String table = (String) list.get(0);
		Connection cx = (Connection) knowledgeCx.g();
		return sqlSelect.t(new Object[]{cx, "SHOW COLUMNS FROM " + table});
	}

	private Object tables() throws Exception
	{
		Connection cx = (Connection) knowledgeCx.g();
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
		if(list == null || list.isEmpty()) throw new Exception("k-count: nom de table manquant");
		String table = (String) list.get(0);
		Connection cx = (Connection) knowledgeCx.g();
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM " + table);
		Object result = rs.next() ? rs.getObject(1) : null;
		st.close();
		return result;
	}

	private Object createGeneric(Object args, String table) throws Exception
	{
		String cmdName = "k-create-" + table.replace("_", "-");
		if(!(args instanceof Map)) throw new Exception(cmdName + ": JSON manquant (utiliser :<json>)");
		Map fields = (Map) args;
		String sql = buildInsert(table, fields);
		Connection cx = (Connection) knowledgeCx.g();
		return sqlInsert.t(new Object[]{cx, sql});
	}

	private Object updateGeneric(Object args, String table) throws Exception
	{
		String cmdName = "k-update-" + table.replace("_", "-");
		if(!(args instanceof Map)) throw new Exception(cmdName + ": JSON manquant (utiliser :<json>)");
		Map fields = new HashMap((Map) args);
		Object id = fields.remove("id");
		if(id == null) throw new Exception(cmdName + ": champ 'id' manquant dans le JSON");
		String sql = buildUpdate(table, fields, id);
		Connection cx = (Connection) knowledgeCx.g();
		return sqlUpdate.t(new Object[]{cx, sql});
	}

	private Object deleteGeneric(Object args, String table) throws Exception
	{
		List list = (List) args;
		String cmdName = "k-delete-" + table.replace("_", "-");
		if(list == null || list.isEmpty()) throw new Exception(cmdName + ": id manquant");
		String id = (String) list.get(0);
		String sql = "DELETE FROM " + table + " WHERE id = " + id;
		Connection cx = (Connection) knowledgeCx.g();
		return sqlDelete.t(new Object[]{cx, sql});
	}

	private Object get(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 2) throw new Exception("k-get: usage: k-get <table> <id>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		String sql   = "SELECT * FROM " + table + " WHERE id = " + id;
		Connection cx = (Connection) knowledgeCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
	}

	private Object list(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("k-list: usage: k-list <table> [limit]");
		String table = (String) list.get(0);
		int limit    = list.size() >= 2 ? Integer.parseInt((String) list.get(1)) : 20;
		String sql   = "SELECT * FROM " + table + " ORDER BY date_created DESC LIMIT " + limit;
		Connection cx = (Connection) knowledgeCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
	}

	private Object search(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 3) throw new Exception("k-search: usage: k-search <table> <field> <value>");
		String table = (String) list.get(0);
		String field = (String) list.get(1);
		String value = (String) list.get(2);
		String sql   = "SELECT * FROM " + table + " WHERE " + field + " LIKE '%" + value.replace("'", "''") + "%'";
		Connection cx = (Connection) knowledgeCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
	}

	private Object tagsOf(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 2) throw new Exception("k-tags-of: usage: k-tags-of <table> <id>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		String fk    = "ID_" + table.toUpperCase();
		String sql   = "SELECT TAG FROM " + table + "_tag WHERE " + fk + " = " + id;
		Connection cx = (Connection) knowledgeCx.g();
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
		if(list == null || list.size() < 3) throw new Exception("k-add-tag: usage: k-add-tag <table> <id> <tag>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		String tag   = (String) list.get(2);
		String fk    = "ID_" + table.toUpperCase();
		String sql   = "INSERT INTO " + table + "_tag (" + fk + ", TAG) VALUES (" + id + ", '" + tag.replace("'", "''") + "')";
		Connection cx = (Connection) knowledgeCx.g();
		return sqlInsert.t(new Object[]{cx, sql});
	}

	private Object removeTag(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 3) throw new Exception("k-remove-tag: usage: k-remove-tag <table> <id> <tag>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		String tag   = (String) list.get(2);
		String fk    = "ID_" + table.toUpperCase();
		String sql   = "DELETE FROM " + table + "_tag WHERE " + fk + " = " + id + " AND TAG = '" + tag.replace("'", "''") + "'";
		Connection cx = (Connection) knowledgeCx.g();
		return sqlDelete.t(new Object[]{cx, sql});
	}

	private Object linksOf(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 2) throw new Exception("k-links-of: usage: k-links-of <table> <id>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		String sql   = "SELECT * FROM " + table + "_link WHERE ID_LINKER = " + id + " OR ID_LINKED = " + id;
		Connection cx = (Connection) knowledgeCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
	}

	private Object addLink(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 3) throw new Exception("k-add-link: usage: k-add-link <table> <id1> <id2> [type]");
		String table = (String) list.get(0);
		String id1   = (String) list.get(1);
		String id2   = (String) list.get(2);
		String type  = list.size() >= 4 ? (String) list.get(3) : "";
		String sql   = "INSERT INTO " + table + "_link (ID_LINKER, ID_LINKED, TYPE) VALUES (" + id1 + ", " + id2 + ", '" + type.replace("'", "''") + "')";
		Connection cx = (Connection) knowledgeCx.g();
		return sqlInsert.t(new Object[]{cx, sql});
	}

	private Object removeLink(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 3) throw new Exception("k-remove-link: usage: k-remove-link <table> <id1> <id2>");
		String table = (String) list.get(0);
		String id1   = (String) list.get(1);
		String id2   = (String) list.get(2);
		String sql   = "DELETE FROM " + table + "_link WHERE ID_LINKER = " + id1 + " AND ID_LINKED = " + id2;
		Connection cx = (Connection) knowledgeCx.g();
		return sqlDelete.t(new Object[]{cx, sql});
	}

	private Object addTodoKnowledge(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 2) throw new Exception("k-add-todo-knowledge: usage: k-add-todo-knowledge <id_todo> <id_knowledge> [type]");
		String idTodo      = (String) list.get(0);
		String idKnowledge = (String) list.get(1);
		String type        = list.size() >= 3 ? (String) list.get(2) : "";
		String sql         = "INSERT INTO todo_knowledge (ID_TODO, ID_KNOWLEDGE, TYPE) VALUES (" + idTodo + ", " + idKnowledge + ", '" + type.replace("'", "''") + "')";
		Connection cx      = (Connection) knowledgeCx.g();
		return sqlInsert.t(new Object[]{cx, sql});
	}

	private Object removeTodoKnowledge(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 2) throw new Exception("k-remove-todo-knowledge: usage: k-remove-todo-knowledge <id_todo> <id_knowledge>");
		String idTodo      = (String) list.get(0);
		String idKnowledge = (String) list.get(1);
		String sql         = "DELETE FROM todo_knowledge WHERE ID_TODO = " + idTodo + " AND ID_KNOWLEDGE = " + idKnowledge;
		Connection cx      = (Connection) knowledgeCx.g();
		return sqlDelete.t(new Object[]{cx, sql});
	}

	private Object detailOf(Object args, String table, boolean hasTags, boolean hasLinks) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("k-detail-of-" + table.replace("_", "-") + ": id manquant");
		String id = (String) list.get(0);
		Connection cx = (Connection) knowledgeCx.g();
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

		if(hasLinks)
		{
			Statement st3 = cx.createStatement();
			ResultSet rs3 = st3.executeQuery("SELECT ID_LINKER, TYPE FROM " + table + "_link WHERE ID_LINKED = " + id);
			List linkers = new ArrayList();
			while(rs3.next())
			{
				Map link = new LinkedHashMap();
				link.put("id", rs3.getObject(1));
				link.put("type", rs3.getString(2));
				linkers.add(link);
			}
			rs3.close();
			st3.close();
			result.put("linkers", linkers);

			Statement st4 = cx.createStatement();
			ResultSet rs4 = st4.executeQuery("SELECT ID_LINKED, TYPE FROM " + table + "_link WHERE ID_LINKER = " + id);
			List linked = new ArrayList();
			while(rs4.next())
			{
				Map link = new LinkedHashMap();
				link.put("id", rs4.getObject(1));
				link.put("type", rs4.getString(2));
				linked.add(link);
			}
			rs4.close();
			st4.close();
			result.put("linked", linked);
		}

		return result;
	}

	private String buildUpdate(String table, Map fields, Object id) throws Exception
	{
		if(fields.isEmpty()) throw new Exception("k-update-" + table.replace("_", "-") + ": aucun champ \u00e0 mettre \u00e0 jour");
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
		return "k-show <table>                        \u2014 colonnes d'une table\n"
			 + "k-count <table>                       \u2014 nombre de lignes\n"
			 + "k-tables                              \u2014 liste des tables\n"
			 + "k-get <table> <id>                    \u2014 un enregistrement par id\n"
			 + "k-list <table> [limit]                \u2014 derniers enregistrements (d\u00e9faut 20)\n"
			 + "k-search <table> <field> <value>      \u2014 recherche LIKE sur un champ\n"
			 + "k-sql <sql>                           \u2014 SQL brut sur knowledgedb1\n"
			 + "k-create-knowledge :<json>            \u2014 ins\u00e8re dans knowledge\n"
			 + "k-create-todo :<json>                 \u2014 ins\u00e8re dans todo\n"
			 + "k-create-doc-x :<json>                \u2014 ins\u00e8re dans doc_x\n"
			 + "k-create-doc-y :<json>                \u2014 ins\u00e8re dans doc_y\n"
			 + "k-create-doc-z :<json>                \u2014 ins\u00e8re dans doc_z\n"
			 + "k-create-knowledge-feedback :<json>   \u2014 ins\u00e8re dans knowledge_feedback\n"
			 + "k-update-knowledge :<json>            \u2014 met \u00e0 jour dans knowledge (id requis)\n"
			 + "k-update-todo :<json>                 \u2014 met \u00e0 jour dans todo (id requis)\n"
			 + "k-update-doc-x :<json>                \u2014 met \u00e0 jour dans doc_x (id requis)\n"
			 + "k-update-doc-y :<json>                \u2014 met \u00e0 jour dans doc_y (id requis)\n"
			 + "k-update-doc-z :<json>                \u2014 met \u00e0 jour dans doc_z (id requis)\n"
			 + "k-update-knowledge-feedback :<json>   \u2014 met \u00e0 jour dans knowledge_feedback (id requis)\n"
			 + "k-delete-knowledge <id>               \u2014 supprime dans knowledge\n"
			 + "k-delete-todo <id>                    \u2014 supprime dans todo\n"
			 + "k-delete-doc-x <id>                   \u2014 supprime dans doc_x\n"
			 + "k-delete-doc-y <id>                   \u2014 supprime dans doc_y\n"
			 + "k-delete-doc-z <id>                   \u2014 supprime dans doc_z\n"
			 + "k-delete-knowledge-feedback <id>      \u2014 supprime dans knowledge_feedback\n"
			 + "k-tags                                \u2014 tous les tags distincts (*_tag)\n"
			 + "k-tags-of <table> <id>                \u2014 tags d'un enregistrement\n"
			 + "k-add-tag <table> <id> <tag>          \u2014 ajouter un tag\n"
			 + "k-remove-tag <table> <id> <tag>       \u2014 supprimer un tag\n"
			 + "k-detail-of-knowledge <id>            \u2014 d\u00e9tail complet (data+tags+linkers+linked)\n"
			 + "k-detail-of-todo <id>                 \u2014 d\u00e9tail complet (data+tags+linkers+linked)\n"
			 + "k-detail-of-doc-x <id>                \u2014 d\u00e9tail complet (data+tags)\n"
			 + "k-detail-of-doc-y <id>                \u2014 d\u00e9tail complet (data+tags)\n"
			 + "k-detail-of-doc-z <id>                \u2014 d\u00e9tail complet (data+tags)\n"
			 + "k-detail-of-knowledge-feedback <id>   \u2014 d\u00e9tail complet (data)\n"
			 + "k-links-of <table> <id>               \u2014 liens d'un enregistrement\n"
			 + "k-add-link <table> <id1> <id2> [type] \u2014 cr\u00e9er un lien\n"
			 + "k-remove-link <table> <id1> <id2>     \u2014 supprimer un lien\n"
			 + "k-add-todo-knowledge <id_todo> <id_knowledge> [type] \u2014 lier un todo \u00e0 un knowledge\n"
			 + "k-remove-todo-knowledge <id_todo> <id_knowledge>     \u2014 supprimer ce lien\n"
			 + "k-help                                \u2014 cette aide";
	}

	private static final String[] TAG_TABLES = {
		"knowledge_tag", "todo_tag", "doc_x_tag", "doc_y_tag", "doc_z_tag"
	};

	private Object tags() throws Exception
	{
		Connection cx = (Connection) knowledgeCx.g();
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
