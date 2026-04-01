package a.entity.gus06.appli.gusexplorer.execute.config.remove;

import a.framework.*;
import javax.swing.JOptionPane;
import java.util.List;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20160827";}
	
	public static final String MESSAGE = "Please, choose a config name to be removed:";
	public static final String TITLE = "Config chooser";


	private Service manager;
	private Service chooser;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.config.manager");
		chooser = Outside.service(this,"gus06.input.choose.dialog");
	}
	
	
	public void e() throws Exception
	{
		List names = (List) manager.g();
		String name = (String) chooser.t(new Object[]{MESSAGE,TITLE,names});
		if(name==null || name.equals("")) return;
		
		manager.v("remove",name);
	}
}
