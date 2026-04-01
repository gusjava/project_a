package a.entity.gus06.appli.gusclient1.gui.space.monitoring.jre;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150323";}


	private Service tabHolder;

	private Service charsetViewer;
	private Service libraryViewer;
	
	
	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
	
		charsetViewer = Outside.service(this,"*gus06.charset.gui.displaygui");
		libraryViewer = Outside.service(this,"*gus06.app.library.gui.displaygui");
		
		tabHolder.v("GUI_charset#Charset",charsetViewer.i());
		tabHolder.v("GUI_lib#Libraries",libraryViewer.i());
	}
	
	
	public Object i() throws Exception
	{return tabHolder.i();}
}
