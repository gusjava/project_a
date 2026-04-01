package a.entity.gus06.app.win.tasklist;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190522";}


	private Service findByPid;
	private Service appPid;

	public EntityImpl() throws Exception
	{
		findByPid = Outside.service(this,"gus06.env.windows.find.tasklist.bypid");
		appPid = Outside.service(this,"gus06.app.pid");
	}
	
	public Object g() throws Exception
	{return findByPid.t(appPid.g());}
}
