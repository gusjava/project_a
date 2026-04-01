package a.entity.gus06.appli.gusclient1.action.space.projects.exportproject;

import a.framework.*;
import javax.swing.Action;

public class EntityImpl implements Entity, G, E {

	public String creationDate() {return "20140910";}

	public static final String DISPLAY = "Export project";


	private Service execute;
	private Service buildAction;
	
	private Action action;

	
	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.appli.gusclient1.execute.space.projects.exportproject");
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
		
		action = (Action) buildAction.t(new Object[]{DISPLAY,this});
	}
	
		
	public Object g() throws Exception
	{return action;}
	
	
	public void e() throws Exception
	{execute.e();}
}
