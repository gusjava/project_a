package a.entity.gus.y.knowledgesys1.perform.link.remove;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service sqlDelete;

	public EntityImpl() throws Exception {
		sqlDelete = Outside.service(this, "gus.y.knowledgedb1.sql.delete");
	}

	public Object t(Object obj) throws Exception {
		Object[] o  = (Object[]) obj;
		Connection cx = (Connection) o[0];
		String table  = (String) o[1];
		String id1    = (String) o[2];
		String id2    = (String) o[3];
		String sql    = "DELETE FROM " + table + "_link WHERE ID_LINKER = " + id1 + " AND ID_LINKED = " + id2;
		return sqlDelete.t(new Object[]{cx, sql});
	}
}