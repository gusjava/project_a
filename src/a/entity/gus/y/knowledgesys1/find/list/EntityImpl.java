package a.entity.gus.y.knowledgesys1.find.list;

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
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String table  = (String) o[1];
		int limit     = Integer.parseInt((String) o[2]);
		
		String sql    = "SELECT * FROM " + table + " ORDER BY date_created DESC LIMIT " + limit;
		return sqlSelect.t(new Object[]{cx, sql});
	}
}