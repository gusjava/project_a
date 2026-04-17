package a.entity.gus.y.server1.engine.cmd.r.sql;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260411";}

	private Service fullString;
	private Service roadmapCx;
	private Service sqlSelect;
	private Service sqlInsert;
	private Service sqlUpdate;
	private Service sqlDelete;

	public EntityImpl() throws Exception
	{
		fullString = Outside.service(this,"gus.y.server1.tool.args.fullstring");
		roadmapCx = Outside.service(this, "gus.y.roadmapdb1.cx.main");
		sqlSelect = Outside.service(this, "gus.y.roadmapdb1.sql.select");
		sqlInsert = Outside.service(this, "gus.y.roadmapdb1.sql.insert");
		sqlUpdate = Outside.service(this, "gus.y.roadmapdb1.sql.update");
		sqlDelete = Outside.service(this, "gus.y.roadmapdb1.sql.delete");
	}

	public Object t(Object obj) throws Exception
	{
		String sql = (String) fullString.t(obj);
		String sql_ = sql.toLowerCase();

		Connection cx = (Connection) roadmapCx.g();
		Object[] params = new Object[]{cx, sql};

		if(sql_.startsWith("show"))   return sqlSelect.t(params);
		if(sql_.startsWith("select")) return sqlSelect.t(params);
		if(sql_.startsWith("insert")) return sqlInsert.t(params);
		if(sql_.startsWith("update")) return sqlUpdate.t(params);
		if(sql_.startsWith("delete")) return sqlDelete.t(params);

		throw new Exception("SQL non supporté: " + sql);
	}
}