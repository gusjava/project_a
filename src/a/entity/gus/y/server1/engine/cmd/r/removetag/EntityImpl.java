package a.entity.gus.y.server1.engine.cmd.r.removetag;

import java.sql.*;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service roadmapCx;
	private Service sqlDelete;

	public EntityImpl() throws Exception
	{
		roadmapCx = Outside.service(this, "gus.y.roadmapdb1.cx.main");
		sqlDelete  = Outside.service(this, "gus.y.roadmapdb1.sql.delete");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size() < 3) throw new Exception("r-removetag: usage: r-removetag <table> <id> <tag>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		String tag   = (String) list.get(2);
		String fk    = "ID_" + table.toUpperCase();
		String sql   = "DELETE FROM " + table + "_tag WHERE " + fk + " = " + id + " AND TAG = '" + tag.replace("'", "''") + "'";
		Connection cx = (Connection) roadmapCx.g();
		return sqlDelete.t(new Object[]{cx, sql});
	}
}