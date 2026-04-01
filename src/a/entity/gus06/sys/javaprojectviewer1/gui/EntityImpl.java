package a.entity.gus06.sys.javaprojectviewer1.gui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20170221";}


	private Service tab;
	private Service gui1;
	private Service gui2;
	

	private JPanel panel;
	
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		gui1 = Outside.service(this,"*gus06.sys.javaprojectviewer1.gui1");
		gui2 = Outside.service(this,"*gus06.sys.javaprojectviewer1.gui2");
		
		
		tab.v("Src Explorer",gui1.i());
		tab.v("Jar Explorer",gui2.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		gui1.p(obj);
		gui2.p(obj);
	}
}
