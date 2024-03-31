package a.entity.gus.y.appli1.fr.action.exit;

import javax.swing.Action;

import a.framework.*;

public class EntityImpl implements Entity, E, G {
	public String creationDate() {return "20240110";}

	public static final String DISPLAY = "ACTION_exit#Quitter";
	
	private Service execute;
	private Service buildAction;
	
	private Action action;

	public EntityImpl() throws Exception {
		execute = Outside.service(this,"gus.y.appli1.fr.execute.exit");
		buildAction = Outside.service(this,"gus.y.swing1.action.builder1");
		
		action = (Action) buildAction.t(new Object[]{DISPLAY, this});
	}
	
	public void e() throws Exception {
		execute.e();
	}
	
	public Object g() throws Exception {
		return action;
	}
}
