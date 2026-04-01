package a.entity.gus06.appli.vindinium.gui.configview.localgame;

import a.framework.*;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20170923";}

	public static final int EDGE = 10;
	

	private Service enemiesGui;
	private Service mapsGui;
	private Service mapGui;
	
	private JPanel panel;

	public EntityImpl() throws Exception
	{
		enemiesGui = Outside.service(this,"*gus06.appli.vindinium.gui.configview.localgame.enemies");
		mapsGui = Outside.service(this,"*gus06.appli.vindinium.gui.configview.localgame.maps");
		mapGui = Outside.service(this,"*gus06.appli.vindinium.gui.configview.localgame.mapchooser");
		
		JPanel p_top = new JPanel(new GridLayout(1,2));
		p_top.add((JComponent) enemiesGui.i());
		p_top.add((JComponent) mapGui.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(p_top,BorderLayout.NORTH);
		panel.add((JComponent) mapsGui.i(),BorderLayout.CENTER);
		
		panel.setBorder(BorderFactory.createEmptyBorder(EDGE,EDGE,EDGE,EDGE));
	}


	public Object i() throws Exception
	{return panel;}
}
