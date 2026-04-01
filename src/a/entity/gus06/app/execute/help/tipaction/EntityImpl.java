package a.entity.gus06.app.execute.help.tipaction;

import a.framework.*;
import javax.swing.Action;

public class EntityImpl implements Entity, G, P, E {

	public String creationDate() {return "20220504";}

	public static final String DISPLAY = "HELP_tip#Help";


	private Service buildAction;
	private Service perform;

	private Action action;
	private String tip;


	public EntityImpl() throws Exception
	{
		buildAction = Outside.service(this,"gus06.swing.action.builder0");
		perform = Outside.service(this,"gus06.app.execute.help");
		
		action = (Action) buildAction.t(new Object[]{DISPLAY,this});
	}
	
	
	public Object g() throws Exception
	{return action;}
	
	
	
	public void p(Object obj) throws Exception
	{
		tip = (String) obj;
		action.setEnabled(tip!=null && !tip.equals(""));
	}
	
	
	
	public void e() throws Exception
	{
		if(tip==null) return;
		perform.p(tip);
	}
}