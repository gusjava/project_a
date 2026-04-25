package a.entity.gus.y.knowledgesys1.find.linksof;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service sqlSelect;

	public EntityImpl() throws Exception {
		sqlSelect = Outside.service(this, "gus.y.knowledgedb1.sql.select");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		Connection cx = (Connection) o[0];
		String table  = (String) o[1];
		String id     = (String) o[2];
		String sql    = "SELECT * FROM " + table + "_link WHERE ID_LINKER = " + id + " OR ID_LINKED = " + id;
		return sqlSelect.t(new Object[]{cx, sql});
	}
}