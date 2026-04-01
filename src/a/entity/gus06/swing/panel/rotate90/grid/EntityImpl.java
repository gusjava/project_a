package a.entity.gus06.swing.panel.rotate90.grid;

import a.framework.*;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20161126";}


	private Service inv;
	private Service reverse;

	public EntityImpl() throws Exception
	{
		inv = Outside.service(this,"gus06.swing.panel.inv.grid");
		reverse = Outside.service(this,"gus06.swing.panel.reverse");
	}

	
	public Object t(Object obj) throws Exception
	{
		p(obj);
		return obj;
	}
	
	public void p(Object obj) throws Exception
	{
		inv.p(obj);
		reverse.p(obj);
	}
}
