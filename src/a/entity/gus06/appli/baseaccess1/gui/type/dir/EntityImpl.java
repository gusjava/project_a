package a.entity.gus06.appli.baseaccess1.gui.type.dir;

import a.framework.*;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150808";}

	private Service base;
	private Service gui;

	public EntityImpl() throws Exception
	{
		base = Outside.service(this,"gus06.sys.base1.holder.dir");
		gui = Outside.service(this,"*gus06.sys.base1.gui.maingui1");
		
		gui.p(base);
	}
	
	public Object i() throws Exception
	{return gui.i();}
}
