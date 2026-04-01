package a.entity.gus06.appli.gusclient1.gui.space.projects.bottombar;

import a.framework.*;
import javax.swing.JComponent;
import java.io.File;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140902";}


	private Service displayLabel;
	
	private JPanel panel;
	
	
	public EntityImpl() throws Exception
	{
		displayLabel = Outside.service(this,"gus06.appli.gusclient1.gui.space.projects.displaylabel");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) displayLabel.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
}
