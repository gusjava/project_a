package a.entity.gus06.appli.gusclient1.gui.space.monitoring;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140719";}


	private Service tabHolder;
	private Service tabPersist;

	private Service threadViewer;
	private Service exceptionViewer;
	private Service actionsViewer;
	private Service systemViewer;
	private Service jreViewer;
	private Service mainViewer;
	private Service entitiesViewer;
	private Service infoViewer;
	private Service appJarViewer;
	
	
	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		tabPersist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
	
		threadViewer = Outside.service(this,"*gus06.thread.gui.viewer");
		exceptionViewer = Outside.service(this,"*gus06.exception.gui.viewer");
		actionsViewer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.monitoring.debug");
		systemViewer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.monitoring.system");
		jreViewer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.monitoring.jre");
		mainViewer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.monitoring.main");
		entitiesViewer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.monitoring.entities");
		infoViewer = Outside.service(this,"*gus06.app.info.gui.viewer");
		appJarViewer = Outside.service(this,"*gus06.app.jarfile.gui.viewer");
		
		tabHolder.v("GUI_thread#Threads",threadViewer.i());
		tabHolder.v("GUI_exception#Exceptions",exceptionViewer.i());
		tabHolder.v("GUI_debug#Actions",actionsViewer.i());
		tabHolder.v("GUI_system#System",systemViewer.i());
		tabHolder.v("GUI_jre#JRE",jreViewer.i());
		tabHolder.v("GUI_main#Main",mainViewer.i());
		tabHolder.v("entity#Entities",entitiesViewer.i());
		tabHolder.v("GUI_info#Infos",infoViewer.i());
		tabHolder.v("GUI_jarFile#App jar",appJarViewer.i());
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	
	public Object i() throws Exception
	{return tabHolder.i();}
}