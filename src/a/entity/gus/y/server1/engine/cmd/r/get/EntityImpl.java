package a.entity.gus.y.server1.engine.cmd.r.get;

import java.sql.*;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service roadmapCx;
	private Service sqlSelect;

	public EntityImpl() throws Exception
	{
		roadmapCx = Outside.service(this, "gus.y.roadmapdb1.cx.main");
		sqlSelect  = Outside.service(this, "gus.y.roadmapdb1.sql.select");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size() < 2) throw new Exception("r-get: usage: r-get <table> <id>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		Connection cx = (Connection) roadmapCx.g();
		return sqlSelect.t(new Object[]{cx, "SELECT * FROM " + table + " WHERE id = " + id});
	}
}