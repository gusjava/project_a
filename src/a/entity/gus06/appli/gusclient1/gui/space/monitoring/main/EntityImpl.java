package a.entity.gus06.appli.gusclient1.gui.space.monitoring.main;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140925";}


	private Service tabHolder;

	private Service mainViewer;
	private Service rbViewer;
	private Service propViewer;
	private Service pathViewer;
	
	
	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
	
		mainViewer = Outside.service(this,"*gus06.app.main.gui.viewer");
		rbViewer = Outside.service(this,"*gus06.app.main.rb.gui.viewer");
		propViewer = Outside.service(this,"*gus06.app.prop.gui.viewer");
		pathViewer = Outside.service(this,"*gus06.app.path.gui.viewer");
		
		tabHolder.v("All",mainViewer.i());
		tabHolder.v("RB",rbViewer.i());
		tabHolder.v("Prop",propViewer.i());
		tabHolder.v("Path",pathViewer.i());
	}
	
	
	public Object i() throws Exception
	{return tabHolder.i();}
}
