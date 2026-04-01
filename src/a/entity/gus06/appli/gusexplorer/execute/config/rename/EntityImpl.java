package a.entity.gus06.appli.gusexplorer.execute.config.rename;

import a.framework.*;
import javax.swing.JOptionPane;
import java.util.List;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20190415";}
	
	public static final String MESSAGE1 = "Please, choose a config name to be renamed:";
	public static final String MESSAGE2 = "Please, choose enter the new name:";
	public static final String TITLE1 = "Config chooser";


	private Service manager;
	private Service chooser;
	private Service input;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.config.manager");
		chooser = Outside.service(this,"gus06.input.choose.dialog");
		input = Outside.service(this,"gus06.input.text.dialog");
	}
	
	
	public void e() throws Exception
	{
		List names = (List) manager.g();
		String name = (String) chooser.t(new Object[]{MESSAGE1,TITLE1,names});
		if(name==null || name.equals("")) return;
		
		String newName = (String) input.t(new String[]{MESSAGE2,name});
		if(newName==null || newName.equals("")) return;
		
		if(newName.equals(name)) return;
		
		manager.v("rename",new String[]{name,newName});
	}
}
