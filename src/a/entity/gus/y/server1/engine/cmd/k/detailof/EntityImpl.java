package a.entity.gus.y.server1.engine.cmd.k.detailof;

import java.sql.*;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service knowledgeCx;

	public EntityImpl() throws Exception
	{
		knowledgeCx = Outside.service(this, "gus.y.knowledgedb1.cx.main");
	}

	public Object t(Object obj) throws Exception
	{
		Map payload  = (Map) obj;
		String table = (String) payload.get("table");
		String id    = (String) payload.get("id");

		if(id == null) throw new Exception("k-detail-of-" + table.replace("_", "-") + ": id manquant");

		boolean hasTags  = !table.equals("knowledge_feedback");
		boolean hasLinks = table.equals("knowledge") || table.equals("todo");

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
}
