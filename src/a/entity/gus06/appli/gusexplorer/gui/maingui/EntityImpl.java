package a.entity.gus06.appli.gusexplorer.gui.maingui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20141206";}

	private Service centralPane;
	private Service bottomGui;

	private JPanel panel;


	public EntityImpl() throws Exception
	{
    	centralPane = Outside.service(this,"gus06.appli.gusexplorer.gui.centralpane");
		bottomGui = Outside.service(this,"gus06.appli.gusexplorer.gui.bottombar");

		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) centralPane.i(),BorderLayout.CENTER);
		panel.add((JComponent) bottomGui.i(),BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
}
