package a.entity.gus.y.knowledgesys1.gui.maingui;

import a.framework.*;

public class EntityImpl implements Entity, I
{
	public String creationDate() {return "20260418";}

	private Service engine;
	private Service tabHolder;
	
	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	private Service gui5;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this, "*gus.y.knowledgesys1.engine");
		tabHolder = Outside.service(this, "*gus.y.swing1.tabbedpane.holder1");
		
		gui1 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui1.knowledge.list");
		gui2 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui2.knowledge.tree");
		gui3 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui3.todo.list");
		gui4 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui4.todo.tree");
		gui5 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui5.tags");

		tabHolder.v("KNOWLEDGE#K-List", gui1);
		tabHolder.v("KNOWLEDGE#K-Tree", gui2);
		tabHolder.v("TODO#T-List", gui3);
		tabHolder.v("TODO#T-Tree", gui4);
		tabHolder.v("TAG#Tags", gui5);
		
		gui1.v("engine", engine);
		gui2.v("engine", engine);
		gui3.v("engine", engine);
		gui4.v("engine", engine);
		gui5.v("engine", engine);
		
		engine.e();
	}

	public Object i() throws Exception
	{return tabHolder.i();}
}