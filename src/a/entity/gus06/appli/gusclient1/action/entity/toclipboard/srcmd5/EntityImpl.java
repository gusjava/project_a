package a.entity.gus06.appli.gusclient1.action.entity.toclipboard.srcmd5;

import a.framework.*;
import javax.swing.Action;

public class EntityImpl implements Entity, G, E {

	public String creationDate() {return "20150301";}

	public static final String DISPLAY = "Source MD5 to clipboard";

	private Service execute;
	private Service buildAction;
	private Service enable;
	
	private Action action;

	
	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.appli.gusclient1.execute.entity.toclipboard.srcmd5");
		enable = Outside.service(this,"gus06.appli.gusclient1.action.entity.cust.enable");
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
		
		action = (Action) buildAction.t(new Object[]{DISPLAY,this});
		enable.p(action);
	}
	
		
	public Object g() throws Exception
	{return action;}
	
	
	public void e() throws Exception
	{execute.e();}
}
