package a.entity.gus06.appli.gusclient1.execute.entity.deploy;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140801";}


	private Service selection;
	private Service deploy;

	public EntityImpl() throws Exception
	{
		selection = Outside.service(this,"gus06.appli.gusclient1.gui.entity.holder");
		deploy = Outside.service(this,"gus06.entitydev.deploy.buildjar");
	}
	
	
	public void e() throws Exception
	{
		String name = (String) selection.g();
		if(name==null) return;
		
		deploy.p(name);
	}
}
