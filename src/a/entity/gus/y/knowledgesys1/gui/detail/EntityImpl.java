package a.entity.gus.y.knowledgesys1.gui.detail;

import a.framework.*;

public class EntityImpl implements Entity, I, P
{
	public String creationDate() {return "20260418";}

	private Service tabHolder;
	private Service gui1;

	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this, "*gus.y.swing1.tabbedpane.holder1");
		gui1 = Outside.service(this, "*gus.y.knowledgesys1.gui.detail.gui1");

		tabHolder.v("Detail", gui1);
	}

	public Object i() throws Exception
	{return tabHolder.i();}
	
	public void p(Object obj) throws Exception
	{
		gui1.p(obj);
	}
}