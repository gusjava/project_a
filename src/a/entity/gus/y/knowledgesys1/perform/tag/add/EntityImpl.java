package a.entity.gus.y.knowledgesys1.perform.tag.add;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service sqlInsert;

	public EntityImpl() throws Exception {
		sqlInsert = Outside.service(this, "gus.y.knowledgedb1.sql.insert");
	}

	public Object t(Object obj) throws Exception {
		Object[] o  = (Object[]) obj;
		Connection cx = (Connection) o[0];
		String table  = (String) o[1];
		String id     = (String) o[2];
		String tag    = (String) o[3];
		String fk     = "ID_" + table.toUpperCase();
		String sql    = "INSERT INTO " + table + "_tag (" + fk + ", TAG) VALUES (" + id + ", '" + tag.replace("'", "''") + "')";
		return sqlInsert.t(new Object[]{cx, sql});
	}
}