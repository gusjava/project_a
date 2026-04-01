package a.entity.gus06.appli.gusexplorer.action.tabs.add.icondir;

import a.framework.*;
import javax.swing.Action;

public class EntityImpl implements Entity, G, E {

	public String creationDate() {return "20160914";}

	public static final String DISPLAY = "ACTION_tab_addIconDir#Add icon dir";

	private Service execute;
	private Service buildAction;
	private Action action;

	
	
	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.appli.gusexplorer.execute.tabs.add.icondir");
		buildAction = Outside.service(this,"gus06.swing.action.builder0");
		
		action = (Action) buildAction.t(new Object[]{DISPLAY,this});
	}
	
	
	public Object g() throws Exception
	{return action;}
	
	
	public void e() throws Exception
	{execute.e();}
}
