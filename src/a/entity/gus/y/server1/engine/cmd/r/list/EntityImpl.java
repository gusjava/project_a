package a.entity.gus.y.server1.engine.cmd.r.list;

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
		if(list == null || list.isEmpty()) throw new Exception("r-list: usage: r-list <table> [limit]");
		String table = (String) list.get(0);
		int limit    = list.size() >= 2 ? Integer.parseInt((String) list.get(1)) : 20;
		Connection cx = (Connection) roadmapCx.g();
		return sqlSelect.t(new Object[]{cx, "SELECT * FROM " + table + " ORDER BY date_created DESC LIMIT " + limit});
	}
}