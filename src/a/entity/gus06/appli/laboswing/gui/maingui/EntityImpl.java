package a.entity.gus06.appli.laboswing.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20160430";}


	private Service tab;
	private Service debugGui;
	private Service persist;
	private Service gui_JTextArea;

	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		debugGui = Outside.service(this,"*gus06.debug.gui.maingui");
		persist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		gui_JTextArea = Outside.service(this,"*gus06.appli.laboswing.gui.jtextarea");
		
		
		tab.v("JTextArea",gui_JTextArea.i());
		tab.v("Debug",debugGui.i());
		
		persist.v(getClass().getName()+"_tab",tab.i());
		
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
}
