package a.entity.gus.y.knowledgesys1.gui.gui2;

import a.framework.*;

public class EntityImpl implements Entity, I, V
{
	public String creationDate() {return "20260429";}

	private Service tabHolder;
	
	private Service gui2_1;
	private Service gui2_2;
	private Service gui2_3;

	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this, "*gus.y.swing1.tabbedpane.holder1");
		
		gui2_1 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui2_1.todo.list");
		gui2_2 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui2_2.todo.treeup");
		gui2_3 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui2_3.todo.treedown");
		
		tabHolder.v("TODO#List", gui2_1);
		tabHolder.v("TODO#Up", gui2_2);
		tabHolder.v("TODO#Down", gui2_3);
	}

	public void v(String key, Object obj) throws Exception
	{
		if (key.equals("engine"))
		{
			gui2_1.v(key, obj);
			gui2_2.v(key, obj);
			gui2_3.v(key, obj);
		}
	}

	public Object i() throws Exception
	{return tabHolder.i();}
}