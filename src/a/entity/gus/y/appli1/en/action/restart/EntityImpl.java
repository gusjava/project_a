package a.entity.gus.y.appli1.en.action.restart;

import javax.swing.Action;

import a.framework.*;

public class EntityImpl implements Entity, E, G {
	public String creationDate() {return "20260409";}

	public static final String DISPLAY = "ACTION_restart#Restart";
	
	private Service execute;
	private Service buildAction;
	
	private Action action;

	public EntityImpl() throws Exception {
		execute = Outside.service(this,"gus.y.appli1.en.execute.restart");
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
