package a.entity.gus.y.knowledgesys1.gui.gui1;

import a.framework.*;

public class EntityImpl implements Entity, I, V
{
	public String creationDate() {return "20260429";}

	private Service tabHolder;

	private Service gui1_1;
	private Service gui1_2;
	private Service gui1_3;
	private Service gui1_4;
	private Service gui1_5;

	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this, "*gus.y.swing1.tabbedpane.holder1");

		gui1_1 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui1_1.knowledge.list");
		gui1_2 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui1_2.knowledge.treeup");
		gui1_3 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui1_3.knowledge.treedown");
		gui1_4 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui1_4.knowledge.tags");
		gui1_5 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui1_5.knowledge.links");

		tabHolder.v("KNOWLEDGE#List",  gui1_1);
		tabHolder.v("KNOWLEDGE#Up",    gui1_2);
		tabHolder.v("KNOWLEDGE#Down",  gui1_3);
		tabHolder.v("KNOWLEDGE#Tags",  gui1_4);
		tabHolder.v("KNOWLEDGE#Links", gui1_5);
	}

	public void v(String key, Object obj) throws Exception
	{
		if (key.equals("engine"))
		{
			gui1_1.v(key, obj);
			gui1_2.v(key, obj);
			gui1_3.v(key, obj);
			gui1_4.v(key, obj);
			gui1_5.v(key, obj);
		}
	}

	public Object i() throws Exception
	{return tabHolder.i();}
}