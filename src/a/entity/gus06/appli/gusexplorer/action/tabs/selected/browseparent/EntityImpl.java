package a.entity.gus06.appli.gusexplorer.action.tabs.selected.browseparent;

import a.framework.*;
import javax.swing.Action;

public class EntityImpl implements Entity, G, E {

	public String creationDate() {return "20160927";}

	public static final String DISPLAY = "ACTION_tab_browseParent#Open parent";

	private Service execute;
	private Service buildAction;
	private Action action;

	
	
	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.appli.gusexplorer.execute.tabs.selected.browseparent");
		buildAction = Outside.service(this,"gus06.swing.action.builder0");
		
		action = (Action) buildAction.t(new Object[]{DISPLAY,this});
	}
	
		
	public Object g() throws Exception
	{return action;}
	
	
	public void e() throws Exception
	{execute.e();}
}
