package a.entity.gus.y.server1.engine.cmd.k;

import java.sql.*;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service knowledgeCx;
	private Service sqlSelect;
	private Service sqlInsert;
	private Service jsonParser;

	public EntityImpl() throws Exception
	{
		knowledgeCx = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		sqlSelect   = Outside.service(this, "gus.y.knowledgedb1.sql.select");
		sqlInsert   = Outside.service(this, "gus.y.knowledgedb1.sql.insert");
		jsonParser  = Outside.service(this, "gus06.file.convert.json.parser");
	}

	public Object t(Object obj) throws Exception
	{
		List args = (List) obj;
		if(args.isEmpty()) throw new Exception("k: commande manquante");

		String cmd = ((String) args.get(0)).toLowerCase();

		if(cmd.equals("show"))            return show(args);
		if(cmd.equals("count"))           return count(args);
		if(cmd.equals("create-knowledge")) return createKnowledge(args);
		if(cmd.equals("help"))            return help();
		if(cmd.equals("tags"))            return tags();

		throw new Exception("k: commande inconnue: " + cmd);
	}

	private Object show(List args) throws Exception
	{
		if(args.size() < 2) throw new Exception("k show: nom de table manquant");
		String table = (String) args.get(1);
		String sql = "SHOW COLUMNS FROM " + table;
		Connection cx = (Connection) knowledgeCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
	}

	private Object count(List args) throws Exception
	{
		if(args.size() < 2) throw new Exception("k count: nom de table manquant");
		String table = (String) args.get(1);
		Connection cx = (Connection) knowledgeCx.g();
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM " + table);
		Object result = rs.next() ? rs.getObject(1) : null;
		st.close();
		return result;
	}

	private Object createKnowledge(List args) throws Exception
	{
		if(args.size() < 2) throw new Exception("k create-knowledge: JSON manquant");
		StringBuffer sb = new StringBuffer();
		for(int i = 1; i < args.size(); i++)
		{
			if(i > 1) sb.append(" ");
			sb.append((String) args.get(i));
		}
		Map fields = (Map) jsonParser.t(sb.toString());
		String sql = buildInsert("knowledge", fields);
		Connection cx = (Connection) knowledgeCx.g();
		return sqlInsert.t(new Object[]{cx, sql});
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
		return "k show <table>             — colonnes d'une table\n"
			 + "k count <table>            — nombre de lignes\n"
			 + "k create-knowledge <json>  — insère une ligne dans knowledge\n"
			 + "k tags                     — tous les tags distincts (toutes tables *_tag)\n"
			 + "k help                     — cette aide";
	}

	private static final String[] TAG_TABLES = {
		"knowledge_tag", "todo_tag", "doc_x_tag", "doc_y_tag", "doc_z_tag", "note_tag"
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
}