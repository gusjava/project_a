package a.entity.gus.y.knowledgesys1.gui.gui3;

import a.framework.*;

public class EntityImpl implements Entity, I, V
{
	public String creationDate() {return "20260429";}

	private Service tabHolder;
	
	private Service gui3_1;

	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this, "*gus.y.swing1.tabbedpane.holder1");
		
		gui3_1 = Outside.service(this, "*gus.y.knowledgesys1.gui.gui3_1.tags");

		tabHolder.v("TAG#Tags", gui3_1);
	}

	public void v(String key, Object obj) throws Exception
	{
		if (key.equals("engine"))
		{
			gui3_1.v(key, obj);
		}
	}

	public Object i() throws Exception
	{return tabHolder.i();}
}