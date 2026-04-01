package a.entity.gus06.app.win.tasklist.mem;

import a.framework.*;

public class EntityImpl implements Entity, G, H {

	public String creationDate() {return "20190607";}


	private Service findByPid;
	private Service appPid;

	public EntityImpl() throws Exception
	{
		findByPid = Outside.service(this,"gus06.env.windows.find.tasklist.mem.bypid");
		appPid = Outside.service(this,"gus06.app.pid");
	}
	
	public Object g() throws Exception
	{return findByPid.t(appPid.g());}
	
	public double h(double value) throws Exception
	{return ((Long) g()).doubleValue();}
}
