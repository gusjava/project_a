package a.entity.gus06.sys.filetool.ext.maven1.holder;

import a.framework.*;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20251217";}

	private Service gui;
	
	public EntityImpl() throws Exception
	{gui = Outside.service(this,"*gus06.y.maven1.gui.main");}
	
	public Object i() throws Exception
	{return gui.i();}
	
	public void p(Object obj) throws Exception
	{gui.p(obj);}
}
