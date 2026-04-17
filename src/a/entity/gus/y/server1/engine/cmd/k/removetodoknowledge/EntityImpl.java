package a.entity.gus.y.server1.engine.cmd.k.removetodoknowledge;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service knowledgeCx;
	private Service sqlDelete;

	public EntityImpl() throws Exception
	{
		knowledgeCx = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		sqlDelete   = Outside.service(this, "gus.y.knowledgedb1.sql.delete");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size() < 2) throw new Exception("k-remove-todo-knowledge: usage: k-remove-todo-knowledge <id_todo> <id_knowledge>");
		String idTodo      = (String) list.get(0);
		String idKnowledge = (String) list.get(1);
		String sql         = "DELETE FROM todo_knowledge WHERE ID_TODO = " + idTodo + " AND ID_KNOWLEDGE = " + idKnowledge;
		Connection cx      = (Connection) knowledgeCx.g();
		return sqlDelete.t(new Object[]{cx, sql});
	}
}