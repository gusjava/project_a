package a.entity.gus06.appli.gusclient1.gui.space.projects.resourcemanager;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140902";}
	
	

	private Service explorer;
	private Service projectManager;
	private Service idToDir;
	
	public EntityImpl() throws Exception
	{
		explorer = Outside.service(this,"*gus06.dir.explorer.resource.dir1");
		projectManager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		idToDir = Outside.service(this,"gus06.appli.gusclient1.project.idtodir.resource");
		
		projectManager.addActionListener(this);
		projectChanged();
	}
	
	
	public Object i() throws Exception
	{return explorer.i();}
	
	
	public void actionPerformed(ActionEvent e)
	{projectChanged();}
	
	
	private void projectChanged()
	{
		try
		{
			String id = (String) projectManager.g();
			File dir = (File) idToDir.t(id);
			explorer.p(dir);
		}
		catch(Exception e)
		{Outside.err(this,"projectChanged()",e);}
	}

}
