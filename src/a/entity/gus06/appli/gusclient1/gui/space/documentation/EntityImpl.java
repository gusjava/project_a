package a.entity.gus06.appli.gusclient1.gui.space.documentation;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140809";}


	private Service tabHolder;
	private Service tabPersist;

	private Service manualViewer;
	private Service tutoViewer;
	private Service appViewer;
	
	
	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		tabPersist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
	
		manualViewer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.documentation.manual");
		tutoViewer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.documentation.tuto");
		appViewer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.documentation.app");
		
		tabHolder.v("GUI_manual#User manual",manualViewer.i());
		tabHolder.v("GUI_tutorial#Tutorials",tutoViewer.i());
		tabHolder.v("GUI_jarFile#Source code",appViewer.i());
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	
	public Object i() throws Exception
	{return tabHolder.i();}
}
