package a.entity.gus06.appli.gusexplorer.execute.tabs.add.temp.fromclipboard.aspath;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20160310";}


	private Service manager;
	private Service fromClipboard;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
		fromClipboard = Outside.service(this,"gus06.sys.clipboard1.g.listfiles2");
	}
	
	
	public void e() throws Exception
	{
		List list = (List) fromClipboard.g();
		manager.v("add",list);
	}
}
