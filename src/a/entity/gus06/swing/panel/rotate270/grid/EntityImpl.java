package a.entity.gus06.swing.panel.rotate270.grid;

import a.framework.*;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20161126";}


	private Service inv;

	public EntityImpl() throws Exception
	{
		inv = Outside.service(this,"gus06.swing.panel.inv.grid");
	}

	
	public Object t(Object obj) throws Exception
	{
		p(obj);
		return obj;
	}
	
	public void p(Object obj) throws Exception
	{
		inv.p(obj);
	}
}
