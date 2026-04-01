package a.entity.gus06.appli.gusclient1.execute.entity.compile;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140801";}


	private Service selection;
	private Service compile;

	public EntityImpl() throws Exception
	{
		selection = Outside.service(this,"gus06.appli.gusclient1.gui.entity.holder");
		compile = Outside.service(this,"gus06.command.entity.compile");
	}
	
	
	public void e() throws Exception
	{
		String name = (String) selection.g();
		if(name==null) return;
		
		compile.p(name);
	}
}
