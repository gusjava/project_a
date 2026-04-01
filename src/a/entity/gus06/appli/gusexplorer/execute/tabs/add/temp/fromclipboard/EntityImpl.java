package a.entity.gus06.appli.gusexplorer.execute.tabs.add.temp.fromclipboard;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20151006";}


	private Service manager;
	private Service fromClipboard;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
		fromClipboard = Outside.service(this,"gus06.sys.clipboard1.g.listfiles");
	}
	
	
	public void e() throws Exception
	{
		List list = (List) fromClipboard.g();
		if(list!=null && list.size()>0) manager.v("add",list);
	}
}