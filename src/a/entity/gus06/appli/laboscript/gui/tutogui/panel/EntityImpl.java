package a.entity.gus06.appli.laboscript.gui.tutogui.panel;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, I, P {

	public String creationDate() {return "20160701";}


	private Service editor;
	
	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.file.editor.ext.gus");
	}
	
	
	public Object i() throws Exception
	{
		return null;
	}
	
	
	public void p(Object obj) throws Exception
	{
		
	}
}