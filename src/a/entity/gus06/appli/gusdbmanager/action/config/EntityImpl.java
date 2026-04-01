package a.entity.gus06.appli.gusdbmanager.action.config;

import javax.swing.Action;
import a.framework.*;

public class EntityImpl implements Entity, G, E {

	public String creationDate() {return "20150613";}

	public static final String DISPLAY = "server_config#Configurer la liste des serveurs";
	
	private Service buildAction;
	private Service execute;
	private Action action;
	

	public EntityImpl() throws Exception
	{
		buildAction = Outside.service(this,"gus06.swing.action.builder0");
		execute = Outside.service(this,"gus06.appli.gusdbmanager.execute.config");
		action = (Action) buildAction.t(new Object[]{DISPLAY,this});
	}

	public Object g() throws Exception
	{return action;}

	public void e() throws Exception
	{execute.e();}
}
