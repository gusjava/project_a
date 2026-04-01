package a.entity.gus06.appli.gusexplorer.gui.tabbedpane.selection;

import a.framework.*;
import java.io.File;

public class EntityImpl extends S1 implements Entity, P, G {

	public String creationDate() {return "20151005";}


	private Service titleChanger;


	private G fileHolder;

	public EntityImpl() throws Exception
	{
		titleChanger = Outside.service(this,"gus06.app.mainframe.titlechanger");
	}
	
	
	public Object g() throws Exception
	{return selectedFile();}
	
	
	
	public void p(Object obj) throws Exception
	{
		fileHolder = (G) obj;
		updateTitle();
		selectionChanged();
	}
	
	
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	
	
	private File selectedFile() throws Exception
	{
		if(fileHolder==null) return null;
		return (File) fileHolder.g();
	}
	
	
	
	private void updateTitle() throws Exception
	{
		File selected = selectedFile();
		if(selected==null) titleChanger.p(null);
		else titleChanger.p(selected.getAbsolutePath());
	}
}
