package a.entity.gus06.appli.gusclient1.execute.entity.load;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140804";}


	private Service selection;
	private Service load;

	public EntityImpl() throws Exception
	{
		selection = Outside.service(this,"gus06.appli.gusclient1.gui.entity.holder");
		load = Outside.service(this,"gus06.app.entity.reload");
	}
	
	
	public void e() throws Exception
	{
		String name = (String) selection.g();
		if(name==null) return;
		
		load.p(name);
	}
}
