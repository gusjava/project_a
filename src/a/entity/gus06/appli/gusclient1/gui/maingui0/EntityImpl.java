package a.entity.gus06.appli.gusclient1.gui.maingui0;


import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140826";}

	private Service centerPanel;
	private Service bottomBar;
	private Service controls;

	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		centerPanel = Outside.service(this,"*gus06.appli.gusclient1.space.shiftpanel");
		bottomBar = Outside.service(this,"*gus06.appli.gusclient1.gui.bottombar");
		controls = Outside.service(this,"gus06.appli.gusclient1.space.controls");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) centerPanel.i(),BorderLayout.CENTER);
		panel.add((JComponent) bottomBar.i(),BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
}
