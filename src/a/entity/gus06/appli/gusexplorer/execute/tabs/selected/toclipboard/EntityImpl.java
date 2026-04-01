package a.entity.gus06.appli.gusexplorer.execute.tabs.selected.toclipboard;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20160904";}


	private Service perform;
	private Service selection;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.clipboard.access.file");
		selection = Outside.service(this,"gus06.appli.gusexplorer.gui.tabbedpane.selection");
	}
	
	
	public void e() throws Exception
	{
		File selected = (File) selection.g();
		if(selected==null) return;
		
		perform.p(selected);
	}
}
