package a.entity.gus.y.knowledgesys1.perform.link.add;

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
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String table  = (String) o[1];
		String id1    = (String) o[2];
		String id2    = (String) o[3];
		String type   = (String) o[4];
		
		String sql = "INSERT INTO " + table + "_link (ID_LINKER, ID_LINKED, TYPE) VALUES (" + id1 + ", " + id2 + ", '" + type.replace("'", "''") + "')";
		return sqlInsert.t(new Object[]{cx, sql});
	}
}