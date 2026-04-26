package a.entity.gus.y.knowledgesys1.perform.todo_tag.add;

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
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Long id = (Long) o[1];
		String tag = (String) o[2];
		
		String sql = "INSERT INTO todo_tag (id_todo, tag) " + 
		"VALUES (" + id + ", '" + tag + "')";
		
		return sqlInsert.t(new Object[]{cx, sql});
	}
}