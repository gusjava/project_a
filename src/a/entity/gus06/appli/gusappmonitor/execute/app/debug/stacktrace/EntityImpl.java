package a.entity.gus06.appli.gusappmonitor.execute.app.debug.stacktrace;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200131";}
	
	public static final String COMMAND_STATE = "stacktrace";


	private Service input;
	
	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
	}


	public void p(Object obj) throws Exception
	{
		String threadId = (String) input.t("Enter thread ID");
		if(threadId==null) return;
		((P)obj).p(COMMAND_STATE+":"+threadId);
	}
}
