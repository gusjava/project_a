package a.entity.gus.y.knowledgesys1.gui.detail1;

import a.framework.*;

public class EntityImpl implements Entity, I, P
{
	public String creationDate() {return "20260418";}

	private Service tabHolder;
	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;

	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this, "*gus.y.swing1.tabbedpane.holder1");
		gui1 = Outside.service(this, "*gus.y.knowledgesys1.gui.detail1.gui1.form");
		gui2 = Outside.service(this, "*gus.y.knowledgesys1.gui.detail1.gui2.uplinks");
		gui3 = Outside.service(this, "*gus.y.knowledgesys1.gui.detail1.gui3.downlinks");
		gui4 = Outside.service(this, "*gus.y.knowledgesys1.gui.detail1.gui4.tags");

		tabHolder.v("Form", gui1);
		tabHolder.v("Up links", gui2);
		tabHolder.v("Down links", gui3);
		tabHolder.v("Tags", gui4);
	}

	public Object i() throws Exception
	{return tabHolder.i();}
	
	public void p(Object obj) throws Exception
	{
		gui1.p(obj);
		gui2.p(obj);
		gui3.p(obj);
		gui4.p(obj);
	}
}