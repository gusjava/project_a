package a.entity.gus06.appli.gusappmonitor.execute.app.restart;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200131";}
	
	public static final String COMMAND_RESTART = "restart";

	public void p(Object obj) throws Exception
	{((P)obj).p(COMMAND_RESTART);}
}
