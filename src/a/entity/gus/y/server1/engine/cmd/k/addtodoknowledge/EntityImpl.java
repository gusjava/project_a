package a.entity.gus.y.server1.engine.cmd.k.addtodoknowledge;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service knowledgeCx;
	private Service sqlInsert;

	public EntityImpl() throws Exception
	{
		knowledgeCx = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		sqlInsert   = Outside.service(this, "gus.y.knowledgedb1.sql.insert");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size() < 2) throw new Exception("k-add-todo-knowledge: usage: k-add-todo-knowledge <id_todo> <id_knowledge> [type]");
		String idTodo      = (String) list.get(0);
		String idKnowledge = (String) list.get(1);
		String type        = list.size() >= 3 ? (String) list.get(2) : "";
		String sql         = "INSERT INTO todo_knowledge (ID_TODO, ID_KNOWLEDGE, TYPE) VALUES (" + idTodo + ", " + idKnowledge + ", '" + type.replace("'", "''") + "')";
		Connection cx      = (Connection) knowledgeCx.g();
		return sqlInsert.t(new Object[]{cx, sql});
	}
}