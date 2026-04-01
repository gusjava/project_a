package a.entity.gus06.appli.labo_tsp.action.newdata;

import a.framework.*;
import java.io.File;
import javax.swing.Action;

public class EntityImpl implements Entity, G, E {

	public String creationDate() {return "20190306";}

	public static final String DISPLAY = "ACTION_newData#New data";


	private Service execute;
	private Service buildAction;
	private Action action;


	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.appli.labo_tsp.execute.newdata");
		buildAction = Outside.service(this,"gus06.swing.action.builder0");
		
		action = (Action) buildAction.t(new Object[]{DISPLAY,this});
	}
	
	
	public Object g() throws Exception
	{return action;}
	
	
	public void e() throws Exception
	{execute.e();}
}
