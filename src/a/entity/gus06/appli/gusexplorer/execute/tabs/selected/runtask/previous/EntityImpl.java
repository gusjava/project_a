package a.entity.gus06.appli.gusexplorer.execute.tabs.selected.runtask.previous;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20220501";}


	private Service runtask;
	private Service selection;
	private Service threadHandler;


	public EntityImpl() throws Exception
	{
		runtask = Outside.service(this,"gus06.sys.runtask1.input.path.previous");
		selection = Outside.service(this,"gus06.appli.gusexplorer.gui.tabbedpane.selection");
		threadHandler = Outside.service(this,"gus06.feature.thread.p");
	}
	
	
	public void e() throws Exception
	{
		File selected = (File) selection.g();
		if(selected==null) return;
		
		threadHandler.p(new Object[]{runtask,selected});
	}
}