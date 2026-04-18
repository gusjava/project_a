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

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this, "*gus.y.knowledgesys1.engine");
		tabHolder = Outside.service(this, "*gus.y.swing1.tabbedpane.holder1");
		
		gui1 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui1.list");
		gui2 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui2.tree");
		gui3 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui3.todo");
		gui4 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui4.tags");

		tabHolder.v("List", gui1);
		tabHolder.v("Tree", gui2);
		tabHolder.v("Todo", gui3);
		tabHolder.v("Tags", gui4);
		
		gui1.v("engine", engine);
		gui2.v("engine", engine);
		gui3.v("engine", engine);
		gui4.v("engine", engine);
		
		engine.e();
	}

	public Object i() throws Exception
	{return tabHolder.i();}
}