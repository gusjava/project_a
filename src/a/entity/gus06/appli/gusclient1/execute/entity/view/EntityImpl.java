package a.entity.gus06.appli.gusclient1.execute.entity.view;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140806";}


	private Service selection;
	private Service view;

	public EntityImpl() throws Exception
	{
		selection = Outside.service(this,"gus06.appli.gusclient1.gui.entity.holder");
		view = Outside.service(this,"gus06.app.entity.viewnew");
	}
	
	
	public void e() throws Exception
	{
		String name = (String) selection.g();
		if(name==null) return;
		
		view.p(name);
	}
}
