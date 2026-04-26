package a.entity.gus.y.knowledgesys1.perform.todo_knowledge.remove;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service sqlDelete;

	public EntityImpl() throws Exception {
		sqlDelete = Outside.service(this, "gus.y.knowledgedb1.sql.delete");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx      = (Connection) o[0];
		Long idTodo      = (Long) o[1];
		Long idKnowledge = (Long) o[2];
		
		String sql = "DELETE FROM todo_knowledge " + 
		"WHERE ID_TODO = " + idTodo + " AND ID_KNOWLEDGE = " + idKnowledge;
		
		return sqlDelete.t(new Object[]{cx, sql});
	}
}