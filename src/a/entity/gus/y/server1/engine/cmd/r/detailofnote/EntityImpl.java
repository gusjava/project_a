package a.entity.gus.y.server1.engine.cmd.r.detailofnote;

import java.sql.*;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service roadmapCx;

	public EntityImpl() throws Exception
	{
		roadmapCx = Outside.service(this, "gus.y.roadmapdb1.cx.main");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.isEmpty()) throw new Exception("r-detailofnote: id manquant");
		String id = (String) list.get(0);
		Connection cx = (Connection) roadmapCx.g();
		Map result = new LinkedHashMap();

		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM note WHERE id = " + id);
		Map data = new LinkedHashMap();
		if(rs.next())
		{
			ResultSetMetaData meta = rs.getMetaData();
			for(int i = 1; i <= meta.getColumnCount(); i++)
				data.put(meta.getColumnName(i).toLowerCase(), rs.getObject(i));
		}
		st.close();
		result.put("data", data);

		Statement st2 = cx.createStatement();
		ResultSet rs2 = st2.executeQuery("SELECT TAG FROM note_tag WHERE ID_NOTE = " + id);
		List tags = new ArrayList();
		while(rs2.next()) tags.add(rs2.getString(1));
		rs2.close();
		st2.close();
		result.put("tags", tags);

		return result;
	}
}