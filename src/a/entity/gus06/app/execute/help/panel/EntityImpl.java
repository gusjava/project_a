package a.entity.gus06.app.execute.help.panel;

import a.framework.*;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20160919";}


	private Service viewer;
	private Service inside;
	
	
	
	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.sys.helpviewer1.gui.maingui");
		inside = Outside.service(this,"gus06.app.inside.help1");
		
		String help = (String) inside.r("main");
		viewer.p(help);
		viewer.v("selectRow","0");
	}
	
	
	public Object i() throws Exception
	{return viewer.i();}
	
	
	public void p(Object obj) throws Exception
	{
		final String tag = "["+obj+"]";
		F f = (F) o->((String)o).contains(tag);
		viewer.v("selectData",f);
	}
}