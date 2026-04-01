package a.entity.gus06.appli.gusexplorer.scripts.startup.manager.executor0;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170103";}


	private Service buildE;
	
	public EntityImpl() throws Exception
	{
		buildE = Outside.service(this,"gus06.sys.script1.build1.e");
	}
	
	
	public void p(Object obj) throws Exception
	{
		E execute = (E) buildE.t(obj);
		execute.e();
	}
}