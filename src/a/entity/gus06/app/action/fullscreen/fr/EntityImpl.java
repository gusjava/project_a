package a.entity.gus06.app.action.fullscreen.fr;

import a.framework.*;
import javax.swing.Action;

public class EntityImpl implements Entity, G, E {

	public String creationDate() {return "20151015";}

	public static final String DISPLAY = "ACTION_fullScreen#Plein �cran";
	
	
	
	private Service execute;
	private Service buildAction;
	
	private Action action;
	

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.app.execute.fullscreen");
		buildAction = Outside.service(this,"gus06.swing.action.builder0");
		
		action = (Action) buildAction.t(new Object[]{DISPLAY,this});
	}



	public Object g() throws Exception
	{return action;}
	
	public void e() throws Exception
	{execute.e();}
}
