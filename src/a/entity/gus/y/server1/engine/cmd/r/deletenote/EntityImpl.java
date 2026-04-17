package a.entity.gus.y.server1.engine.cmd.r.deletenote;

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
		if(list == null || list.isEmpty()) throw new Exception("r-deletenote: id manquant");
		String id = (String) list.get(0);
		Connection cx = (Connection) roadmapCx.g();
		return sqlDelete.t(new Object[]{cx, "DELETE FROM note WHERE id = " + id});
	}
}