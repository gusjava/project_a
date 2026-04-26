package a.entity.gus.y.knowledgesys1.perform.knowledge_link.remove;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260426";}

	private Service sqlDelete;

	public EntityImpl() throws Exception
	{
		sqlDelete = Outside.service(this, "gus.y.knowledgedb1.sql.delete");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o  = (Object[]) obj;
		if (o.length != 3) throw new Exception("Wrong data number: " + o.length);
		
		Connection cx = (Connection) o[0];
		Long id1    = (Long) o[2];
		Long id2    = (Long) o[3];
		
		String sql = "DELETE FROM knowledge_link " + 
		"WHERE ID_LINKER = " + id1 + " AND ID_LINKED = " + id2;
		
		return sqlDelete.t(new Object[]{cx, sql});
	}
}