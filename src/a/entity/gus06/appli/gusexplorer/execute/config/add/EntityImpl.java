package a.entity.gus06.appli.gusexplorer.execute.config.add;

import a.framework.*;
import javax.swing.JOptionPane;
import java.util.List;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20160420";}
	
	public static final String MESSAGE = "You are about to overwrite a config. Proceed ?";


	private Service manager;
	private Service performSave;
	private Service confirm;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.config.manager");
		performSave = Outside.service(this,"gus06.appli.gusexplorer.config.perform.save");
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
	}
	
	
	public void e() throws Exception
	{
		String name = input();
		if(name==null || name.equals("")) return;
		
		List names = (List) manager.g();
		if(names.contains(name))
		{
			if(!confirm.f(MESSAGE)) return;
		}
		
		performSave.p(name);
	}
	
	private String input()
	{
		String title = "Config's name:";
		return JOptionPane.showInputDialog(null,"",title,JOptionPane.PLAIN_MESSAGE);
	}
}