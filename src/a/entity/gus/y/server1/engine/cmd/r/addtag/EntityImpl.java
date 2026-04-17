package a.entity.gus.y.server1.engine.cmd.r.addtag;

import java.sql.*;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service roadmapCx;
	private Service sqlInsert;

	public EntityImpl() throws Exception
	{
		roadmapCx = Outside.service(this, "gus.y.roadmapdb1.cx.main");
		sqlInsert  = Outside.service(this, "gus.y.roadmapdb1.sql.insert");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size() < 3) throw new Exception("r-addtag: usage: r-addtag <table> <id> <tag>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		String tag   = (String) list.get(2);
		String fk    = "ID_" + table.toUpperCase();
		String sql   = "INSERT INTO " + table + "_tag (" + fk + ", TAG) VALUES (" + id + ", '" + tag.replace("'", "''") + "')";
		Connection cx = (Connection) roadmapCx.g();
		return sqlInsert.t(new Object[]{cx, sql});
	}
}