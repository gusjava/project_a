package a.entity.gus06.appli.labo_tsp.gui.data;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20190301";}


	private Service screen;
	private Service area;


	private JPanel panel;


	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.appli.labo_tsp.data.edit.screen");
		area = Outside.service(this,"*gus06.appli.labo_tsp.data.edit.textarea");
		
		panel = new JPanel(new BorderLayout());
		
		JSplitPane split = new JSplitPane();
		split.setLeftComponent((JComponent) area.i());
		split.setRightComponent((JComponent) screen.i());
		split.setDividerLocation(0.5);
		
		panel.add(split,BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
}
