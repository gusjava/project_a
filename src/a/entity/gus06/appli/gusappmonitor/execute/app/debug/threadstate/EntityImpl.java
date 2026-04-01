package a.entity.gus06.appli.gusappmonitor.execute.app.debug.threadstate;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200131";}
	
	public static final String COMMAND_STATE = "threadstate";

	public void p(Object obj) throws Exception
	{((P)obj).p(COMMAND_STATE);}
}