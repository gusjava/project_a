package a.entity.gus.y.knowledgesys1.engine;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, E, R {
	public String creationDate() {return "20260414";}

	private Service cxMain;
	
	private Service findallKnowledgeRoots;
	private Service findallKnowledgeLeafs;
	private Service findallKnowledgeList;
	private Service findallKnowledgeTags;
	
	private Service findallTodoRoots;
	private Service findallTodoLeafs;
	private Service findallTodoList;

	private Connection cx;
	
	private List knowledgeRoots;
	private List knowledgeLeafs;
	private List knowledgeList;
	private List knowledgeLinks;
	private Map knowledgeTags;
	
	private List todoRoots;
	private List todoLeafs;
	private List todoList;
	private List todoLinks;
	private Map todoTags;

	public EntityImpl() throws Exception
	{
		cxMain = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		
		findallKnowledgeRoots = Outside.service(this, "gus.y.knowledgedb1.knowledge.findall.roots");
		findallKnowledgeLeafs = Outside.service(this, "gus.y.knowledgedb1.knowledge.findall.leafs");
		findallKnowledgeList = Outside.service(this, "gus.y.knowledgedb1.knowledge.findall");
		findallKnowledgeTags = Outside.service(this, "gus.y.knowledgedb1.knowledge_tag.countbytag");
		
		findallTodoRoots = Outside.service(this, "gus.y.knowledgedb1.todo.findall.roots");
		findallTodoLeafs = Outside.service(this, "gus.y.knowledgedb1.todo.findall.leafs");
		findallTodoList = Outside.service(this, "gus.y.knowledgedb1.todo.findall");
		
		load();
	}

	public void e() throws Exception
	{
		new Thread(() -> {load();}).start();
	}
	
	private void load()
	{
		try
		{
			cx = (Connection) cxMain.g();
			
			knowledgeRoots = (List) findallKnowledgeRoots.t(cx);
			knowledgeLeafs = (List) findallKnowledgeLeafs.t(cx);
			knowledgeList = (List) findallKnowledgeList.t(cx);
			knowledgeTags = (Map) findallKnowledgeTags.t(cx);
			
			todoRoots = (List) findallTodoRoots.t(cx);
			todoLeafs = (List) findallTodoLeafs.t(cx);
			todoList = (List) findallTodoList.t(cx);
			
			loaded();
		}
		catch(Exception e)
		{Outside.err(this,"load()",e);}
	}

	public Object r(String key) throws Exception
	{
		if (key.equals("cx")) return cx;
		
		if (key.equals("knowledgeRoots")) return knowledgeRoots;
		if (key.equals("knowledgeLeafs")) return knowledgeLeafs;
		if (key.equals("knowledgeList")) return knowledgeList;
		if (key.equals("knowledgeTags")) return knowledgeTags;
		
		if (key.equals("todoRoots")) return todoRoots;
		if (key.equals("todoLeafs")) return todoLeafs;
		if (key.equals("todoList")) return todoList;

		if (key.equals("keys")) return new String[] 
			{"cx", 
			
			"knowledgeRoots", 
			"knowledgeLeafs", 
			"knowledgeList", 
			"knowledgeTags", 
			
			"todoRoots", 
			"todoLeafs", 
			"todoList"};
			
		throw new Exception("Unknown key: " + key);
	}

	private void loaded()
	{send(this, "loaded()");}
}
