package a.entity.gus.y.knowledgesys1.engine;

import java.sql.Connection;
import java.util.List;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, E, R {
	public String creationDate() {return "20260414";}

	private Service cxMain;
	
	private Service findallKnowledgeRoots;
	private Service findallKnowledgeList;
	
	private Service findallTodoRoot;
	private Service findallTodoList;

	private Connection cx;
	
	private List knowledgeRoots;
	private List knowledgeList;
	
	private List todoRoots;
	private List todoList;

	public EntityImpl() throws Exception {
		cxMain = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		
		findallKnowledgeRoots = Outside.service(this, "gus.y.knowledgedb1.knowledge.findall.roots");
		findallKnowledgeList = Outside.service(this, "gus.y.knowledgedb1.knowledge.findall");
		
		findallTodoRoot = Outside.service(this, "gus.y.knowledgedb1.todo.findall.roots");
		findallTodoList = Outside.service(this, "gus.y.knowledgedb1.todo.findall");
	}

	public void e() throws Exception {
		cx = (Connection) cxMain.g();
		
		knowledgeRoots = (List) findallKnowledgeRoots.t(cx);
		knowledgeList = (List) findallKnowledgeList.t(cx);
		
		todoRoots = (List) findallTodoRoot.t(cx);
		todoList = (List) findallTodoList.t(cx);
		
		loaded();
	}

	private void loaded() {
		send(this, "loaded()");
	}

	public Object r(String key) throws Exception {
		if (key.equals("cx")) return cx;
		
		if (key.equals("knowledgeRoots")) return knowledgeRoots;
		if (key.equals("knowledgeList")) return knowledgeList;
		
		if (key.equals("todoRoots")) return todoRoots;
		if (key.equals("todoList")) return todoList;

		if (key.equals("keys")) return new String[] 
			{"cx", 
			"knowledgeRoots", 
			"knowledgeList", 
			"todoRoots", 
			"todoList"};
			
		throw new Exception("Unknown key: " + key);
	}
}
