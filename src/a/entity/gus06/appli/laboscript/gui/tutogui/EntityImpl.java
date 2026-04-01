package a.entity.gus06.appli.laboscript.gui.tutogui;

import a.framework.*;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20160630";}
	
	public static final String KEY = "scriptgus";


	private Service gui;
	private Service panel;
	


	public EntityImpl() throws Exception
	{
		gui = Outside.service(this,"*gus06.sys.tuto1.gui");
		panel = Outside.service(this,"*gus06.appli.laboscript.gui.tutogui.panel");
		
		gui.v("load",KEY);
		gui.v("validator",panel);
	}
	
	
	public Object i() throws Exception
	{return gui.i();}
}
