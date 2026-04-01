package a.entity.gus06.appli.gusclient1.gui.space.monitoring.entities;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140808";}


	private Service tabHolder;

	private Service entityMapViewer;
	private Service classMapViewer;
	private Service jarMapViewer;
	
	
	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
	
		entityMapViewer = Outside.service(this,"*gus06.app.entitymap.gui.viewer");
		classMapViewer = Outside.service(this,"*gus06.app.classmap.gui.viewer");
		jarMapViewer = Outside.service(this,"*gus06.app.jarmap.gui.viewer");
		
		tabHolder.v("GUI_entityObj#Object map",entityMapViewer.i());
		tabHolder.v("GUI_entityClass#Class map",classMapViewer.i());
		tabHolder.v("GUI_entityJar#Jar map",jarMapViewer.i());
	}
	
	
	public Object i() throws Exception
	{return tabHolder.i();}
}
