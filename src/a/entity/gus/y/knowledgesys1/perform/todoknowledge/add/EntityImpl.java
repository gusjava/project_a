package a.entity.gus.y.knowledgesys1.perform.todoknowledge.add;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service sqlInsert;

	public EntityImpl() throws Exception {
		sqlInsert = Outside.service(this, "gus.y.knowledgedb1.sql.insert");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx      = (Connection) o[0];
		String idTodo      = (String) o[1];
		String idKnowledge = (String) o[2];
		String type        = (String) o[3];
		
		String sql         = "INSERT INTO todo_knowledge (ID_TODO, ID_KNOWLEDGE, TYPE) VALUES (" + idTodo + ", " + idKnowledge + ", '" + type.replace("'", "''") + "')";
		return sqlInsert.t(new Object[]{cx, sql});
	}
}