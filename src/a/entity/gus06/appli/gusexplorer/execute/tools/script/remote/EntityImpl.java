package a.entity.gus06.appli.gusexplorer.execute.tools.script.remote;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20170421";}
	
	public static final String TITLE = "Information for remote access";


	private Service executor;
	private Service input;

	public EntityImpl() throws Exception
	{
		executor = Outside.service(this,"gus06.appli.gusexplorer.scripts.remote.executor");
		input = Outside.service(this,"gus06.input.textarea.dialog.clipboard");
	}
	
	public void e() throws Exception
	{
		String infos = (String) input.t(TITLE);
		if(infos!=null) executor.p(infos);
	}
}
