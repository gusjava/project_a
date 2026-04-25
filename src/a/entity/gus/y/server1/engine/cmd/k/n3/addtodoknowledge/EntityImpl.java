package a.entity.gus.y.server1.engine.cmd.k.n3.addtodoknowledge;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service todoKnowledgeAdd;
	private Service knowledgeEngine;

	public EntityImpl() throws Exception {
		todoKnowledgeAdd = Outside.service(this, "gus.y.knowledgesys1.perform.todoknowledge.add");
		knowledgeEngine  = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception {
		List list = (List) obj;
		if(list == null || list.size() < 2)
			throw new Exception("k-add-todo-knowledge: usage: k-add-todo-knowledge <id_todo> <id_knowledge> [type]");
		String idTodo      = (String) list.get(0);
		String idKnowledge = (String) list.get(1);
		String type        = list.size() >= 3 ? (String) list.get(2) : "";
		return todoKnowledgeAdd.t(new Object[]{cx(), idTodo, idKnowledge, type});
	}

	private Connection cx() throws Exception
	{return (Connection) knowledgeEngine.r("cx");}
}