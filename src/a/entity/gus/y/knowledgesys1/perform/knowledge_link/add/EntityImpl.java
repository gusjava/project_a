package a.entity.gus.y.knowledgesys1.perform.knowledge_link.add;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260426";}

	private Service sqlInsert;

	public EntityImpl() throws Exception
	{
		sqlInsert = Outside.service(this, "gus.y.knowledgedb1.sql.insert");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o  = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Long id1    = (Long) o[1];
		Long id2    = (Long) o[2];
		String type   = (String) o[3];
		
		String sql = "INSERT INTO knowledge_link (ID_LINKER, ID_LINKED, TYPE) " + 
		"VALUES (" + id1 + ", " + id2 + ", '" + type + "')";
		
		return sqlInsert.t(new Object[]{cx, sql});
	}
}