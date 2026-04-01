package a.entity.gus06.appli.gusexplorer.execute.config.replace;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20160829";}
	
	public static final String MESSAGE = "Please, choose a config name to be replaced:";
	public static final String TITLE = "Config chooser";


	private Service manager;
	private Service performSave;
	private Service chooser;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.config.manager");
		performSave = Outside.service(this,"gus06.appli.gusexplorer.config.perform.save");
		chooser = Outside.service(this,"gus06.input.choose.dialog");
	}
	
	
	public void e() throws Exception
	{
		List names = (List) manager.g();
		String name = (String) chooser.t(new Object[]{MESSAGE,TITLE,names});
		if(name==null || name.equals("")) return;
		
		performSave.p(name);
	}
}