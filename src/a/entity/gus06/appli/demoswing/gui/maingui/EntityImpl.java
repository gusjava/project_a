package a.entity.gus06.appli.demoswing.gui.maingui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20141028";}


	private Service tabHolder;
	private Service tabPersist;
	private Service guiBuilder;


	private JPanel panel;

	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		tabPersist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		guiBuilder = Outside.service(this,"gus06.appli.demoswing.gui.builder");
		
		tabHolder.v("SWING_JTree#JTree",gui("tree"));
		tabHolder.v("SWING_JTabbedPane#JTabbedPane",gui("tabbedpane"));
		tabHolder.v("SWING_JSplitPane#JSplitPane",gui("splitpane"));
		tabHolder.v("SWING_JTable#JTable",gui("table"));
		tabHolder.v("SWING_JTextField#JTextField",gui("textfield"));
		tabHolder.v("SWING_JProgressBar#JProgressBar",gui("progressbar"));
		tabHolder.v("SWING_JScrollBar#JScrollBar",gui("scrollpane"));
		tabHolder.v("SWING_JPanel#JPanel",gui("panel"));
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tabHolder.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	private JComponent gui(String id) throws Exception
	{return (JComponent) guiBuilder.t(id);}
}
