package a.entity.gus06.appli.gusexplorer.execute.tabs.selected.reload;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20151006";}


	private Service manager;
	private Service selection;
	private Service threadHandler;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
		selection = Outside.service(this,"gus06.appli.gusexplorer.gui.tabbedpane.selection");
		threadHandler = Outside.service(this,"gus06.feature.thread.v");
	}
	
	
	public void e() throws Exception
	{
		File selected = (File) selection.g();
		if(selected==null) return;
		
		threadHandler.p(new Object[]{manager,"reload",selected});
	}
}
