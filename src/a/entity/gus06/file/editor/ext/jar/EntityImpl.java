package a.entity.gus06.file.editor.ext.jar;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140804";}

	private Service tab;
	private Service listingGui;
	private Service manifestGui;
	private Service depGui;
	private Service mavenGui;
	
	private File file;

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		listingGui = Outside.service(this,"*gus06.file.editor.ext.jar.viewer.listing");
		manifestGui = Outside.service(this,"*gus06.file.editor.ext.jar.viewer.manifest");
		depGui = Outside.service(this,"*gus06.file.editor.ext.jar.viewer.dependencies");
		mavenGui = Outside.service(this,"*gus06.file.editor.ext.jar.viewer.maven");
		
		tab.v("Listing",listingGui.i());
		tab.v("Manifest",manifestGui.i());
		tab.v("Dependencies",depGui.i());
		tab.v("Maven",mavenGui.i());
	}
	
	public Object i() throws Exception
	{return tab.i();}
	
	public Object g() throws Exception
	{return file;}
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile() || file.length()==0) resetGui();
		else updateGui();
	}
	
	private void resetGui() throws Exception
	{
		listingGui.p(null);
		manifestGui.p(null);
		depGui.p(null);
		mavenGui.p(null);
	}
	
	private void updateGui() throws Exception
	{
		listingGui.p(file);
		manifestGui.p(file);
		depGui.p(file);
		mavenGui.p(file);
	}
}