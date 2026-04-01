package a.entity.gus06.appli.gusclient1.gui.space.projects.displaylabel;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20141028";}


	private Service projectManager;
	
	private JLabel label;
	
	
	public EntityImpl() throws Exception
	{
		projectManager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		
		label = new JLabel(" ");
		
		projectManager.addActionListener(this);
		projectChanged();
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	public void actionPerformed(ActionEvent e)
	{projectChanged();}
	
	
	
	private void projectChanged()
	{
		try
		{
			String id = (String) projectManager.g();
			if(id==null) label.setText(" ");
			else label.setText(" Project "+id);
		}
		catch(Exception e)
		{Outside.err(this,"projectChanged()",e);}
	}

}
