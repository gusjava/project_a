package a.entity.gus06.appli.gusexplorer.execute.tools.icons.reload;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20201206";}
	

	private Service provider;

	public EntityImpl() throws Exception
	{
		provider = Outside.service(this,"gus06.icon.provider");
	}
	
	public void e() throws Exception
	{
		provider.e();
	}
}