package a.entity.gus06.appli.ideamanager.gui.maingui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150425";}


	private Service tab;
	private Service persist;
	private Service iconViewer;
	private Service gui1;
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		iconViewer = Outside.service(this,"*gus06.appli.ideamanager.gui.iconviewer");
		gui1 = Outside.service(this,"*gus06.appli.ideamanager.gui.gui1");
		persist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		tab.v("editor2#Editor",gui1.i());
		tab.v("icons#Icons",iconViewer.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
}
