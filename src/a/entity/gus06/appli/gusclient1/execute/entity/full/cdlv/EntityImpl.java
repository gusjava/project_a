package a.entity.gus06.appli.gusclient1.execute.entity.full.cdlv;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140808";}


	private Service selection;

	private Service compile;
	private Service deploy;
	private Service load;
	private Service view;
	

	public EntityImpl() throws Exception
	{
		selection = Outside.service(this,"gus06.appli.gusclient1.gui.entity.holder");
	
		compile = Outside.service(this,"gus06.command.entity.compile");
		deploy = Outside.service(this,"gus06.entitydev.deploy.buildjar");
		load = Outside.service(this,"gus06.app.entity.reload");
		view = Outside.service(this,"gus06.app.entity.viewnew");
	}
	
	
	public void e() throws Exception
	{
		String name = (String) selection.g();
		if(name==null) return;
		
		compile.p(name);
		deploy.p(name);
		load.p(name);
		view.p(name);
	}
}
