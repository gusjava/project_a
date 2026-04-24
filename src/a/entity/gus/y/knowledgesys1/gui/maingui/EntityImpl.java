package a.entity.gus.y.knowledgesys1.gui.maingui;

import a.framework.*;

public class EntityImpl implements Entity, I
{
	public String creationDate() {return "20260418";}

	private Service engine;
	private Service tabHolder;
	
	private Service gui1_1;
	private Service gui1_2;
	private Service gui1_3;
	
	private Service gui2_1;
	private Service gui2_2;
	private Service gui2_3;
	
	private Service gui3_1;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this, "*gus.y.knowledgesys1.engine");
		tabHolder = Outside.service(this, "*gus.y.swing1.tabbedpane.holder1");
		
		gui1_1 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui1_1.knowledge.list");
		gui1_2 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui1_2.knowledge.treeup");
		gui1_3 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui1_3.knowledge.treedown");
		
		gui2_1 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui2_1.todo.list");
		gui2_2 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui2_2.todo.treeup");
		gui2_3 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui2_3.todo.treedown");
		
		gui3_1 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui3_1.tags");

		tabHolder.v("KNOWLEDGE#K-List", gui1_1);
		tabHolder.v("KNOWLEDGE#K-Up", gui1_2);
		tabHolder.v("KNOWLEDGE#K-Down", gui1_3);
		
		tabHolder.v("TODO#T-List", gui2_1);
		tabHolder.v("TODO#T-Up", gui2_2);
		tabHolder.v("TODO#T-Down", gui2_3);
		
		tabHolder.v("TAG#Tags", gui3_1);
		
		gui1_1.v("engine", engine);
		gui1_2.v("engine", engine);
		gui1_3.v("engine", engine);
		
		gui2_1.v("engine", engine);
		gui2_2.v("engine", engine);
		gui2_3.v("engine", engine);
		
		gui3_1.v("engine", engine);
		
		engine.e();
	}

	public Object i() throws Exception
	{return tabHolder.i();}
}