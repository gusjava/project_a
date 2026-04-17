package a.entity.gus.y.server1.engine.cmd.r.count;

import java.sql.*;
import java.util.List;
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
		if(list == null || list.isEmpty()) throw new Exception("r-count: nom de table manquant");
		String table = (String) list.get(0);
		Connection cx = (Connection) roadmapCx.g();
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM " + table);
		Object result = rs.next() ? rs.getObject(1) : null;
		st.close();
		return result;
	}
}