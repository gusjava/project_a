package a.entity.gus.z.server1.engine.cmd.k;

import java.sql.*;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service knowledgeCx;
	private Service sqlSelect;

	public EntityImpl() throws Exception
	{
		knowledgeCx = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		sqlSelect = Outside.service(this, "gus.y.knowledgedb1.sql.select");
	}

	public Object t(Object obj) throws Exception
	{
		List args = (List) obj;
		if(args.isEmpty()) throw new Exception("k: commande manquante");

		String cmd = ((String) args.get(0)).toLowerCase();

		if(cmd.equals("show"))  return show(args);
		if(cmd.equals("count")) return count(args);
		if(cmd.equals("help"))  return help();
		if(cmd.equals("tags"))  return tags();

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
		String sql = "SELECT COUNT(*) FROM " + table;
		Connection cx = (Connection) knowledgeCx.g();
		return sqlSelect.t(new Object[]{cx, sql});
	}

	private Object help()
	{
		return "k show <table>  — colonnes d'une table\n"
			 + "k count <table> — nombre de lignes\n"
			 + "k tags          — tous les tags distincts (toutes tables *_tag)\n"
			 + "k help          — cette aide";
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
