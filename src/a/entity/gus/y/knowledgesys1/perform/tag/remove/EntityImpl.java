package a.entity.gus.y.knowledgesys1.perform.tag.remove;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service sqlDelete;

	public EntityImpl() throws Exception
	{
		sqlDelete = Outside.service(this, "gus.y.knowledgedb1.sql.delete");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o  = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String table  = (String) o[1];
		Long id     = (Long) o[2];
		String tag    = (String) o[3];
		
		String fk     = "ID_" + table.toUpperCase();
		String sql    = "DELETE FROM " + table + "_tag WHERE " + fk + " = " + id + " AND TAG = '" + tag.replace("'", "''") + "'";
		
		return sqlDelete.t(new Object[]{cx, sql});
	}
}