package a.entity.gus.y.server1.engine.cmd.k.show;

import java.sql.*;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service knowledgeCx;
	private Service sqlSelect;

	public EntityImpl() throws Exception
	{
		knowledgeCx = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		sqlSelect   = Outside.service(this, "gus.y.knowledgedb1.sql.select");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.isEmpty()) throw new Exception("k-show: nom de table manquant");
		String table = (String) list.get(0);
		Connection cx = (Connection) knowledgeCx.g();
		return sqlSelect.t(new Object[]{cx, "SHOW COLUMNS FROM " + table});
	}
}