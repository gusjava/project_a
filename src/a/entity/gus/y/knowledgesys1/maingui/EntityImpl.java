package a.entity.gus.y.knowledgesys1.maingui;

import a.framework.*;

public class EntityImpl implements Entity, I
{
	public String creationDate() {return "20260418";}

	private Service engine;
	private Service tabHolder;
	private Service gui2;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this, "*gus.y.knowledgesys1.maingui.engine");
		tabHolder = Outside.service(this, "*gus.y.swing1.tabbedpane.holder1");
		gui2 = Outside.service(this, "*gus.y.knowledgesys1.maingui.gui2");

		tabHolder.v("Tree", gui2);
		
		gui2.v("engine", engine);
		
		engine.e();
	}

	public Object i() throws Exception {return tabHolder.i();}
}