package a.entity.gus.y.server1.watchdog;

import a.framework.*;

public class EntityImpl implements Entity, T
{
	public String creationDate() {return "20260410";}

	private Service engine;
	private Service buildWatchdog;
	private T watchdog;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this, "gus.y.server1.engine");
		buildWatchdog = Outside.service(this, "gus.x.thread.watchdog");
		watchdog = (T) buildWatchdog.t(new Object[]{engine, 30L});
	}

	public Object t(Object obj) throws Exception
	{
		return watchdog.t(obj);
	}
}
