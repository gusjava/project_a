package a.entity.gus06.sys.dirsearch1.gui.maingui2;

import a.framework.*;

public class EntityImpl implements Entity, P, I, E {

	public String creationDate() {return "20221015";}


	private Service gui;

	public EntityImpl() throws Exception
	{gui = Outside.service(this,"*gus06.sys.dirsearch1.gui.maingui1");}
	
	public Object i() throws Exception
	{return gui.i();}
	
	public void e() throws Exception
	{gui.e();}
	
	public void p(Object obj) throws Exception
	{gui.v("roots",obj);}
}