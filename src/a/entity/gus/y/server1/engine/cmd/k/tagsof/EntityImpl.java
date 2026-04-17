package a.entity.gus.y.server1.engine.cmd.k.tagsof;

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
		List list = (List) obj;
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
}