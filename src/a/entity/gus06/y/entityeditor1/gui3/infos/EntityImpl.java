package a.entity.gus06.y.entityeditor1.gui3.infos;

import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20251121";}

	private Service tabHolder;
	private Service gui1;
	private Service gui2;
	private Service gui3;

	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this, "*gus.swing.tabbedpane.holder1");
		gui1 = Outside.service(this, "*gus.y.entityeditor1.gui3.infos.calls");
		gui2 = Outside.service(this, "*gus.y.entityeditor1.gui3.infos.downlinks");
		gui3 = Outside.service(this, "*gus.y.entityeditor1.gui3.infos.uplinks");

		tabHolder.v("Down links", gui2);
		tabHolder.v("Up links", gui3);
		tabHolder.v("Calls", gui1);
	}

	public void p(Object obj) throws Exception
	{
		gui1.p(obj);
		gui2.p(obj);
		gui3.p(obj);
	}

	public Object i() throws Exception
	{return tabHolder.i();}
}
