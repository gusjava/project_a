package a.entity.gus.y.server1.engine.cmd.k.removetodoknowledge;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service todoKnowledgeRemove;
	private Service knowledgeEngine;

	public EntityImpl() throws Exception {
		todoKnowledgeRemove = Outside.service(this, "gus.y.knowledgesys1.perform.todoknowledge.remove");
		knowledgeEngine     = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception {
		List list = (List) obj;
		if(list == null || list.size() < 2) throw new Exception("k-remove-todo-knowledge: usage: k-remove-todo-knowledge <id_todo> <id_knowledge>");
		String idTodo      = (String) list.get(0);
		String idKnowledge = (String) list.get(1);
		return todoKnowledgeRemove.t(new Object[]{cx(), idTodo, idKnowledge});
	}

	private Connection cx() throws Exception
	{return (Connection) knowledgeEngine.r("cx");}
}