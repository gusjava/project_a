package a.entity.gus.y.server1.engine.cmd.restart;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service execute1s;
	private Service restart;

	public EntityImpl() throws Exception
	{
		execute1s = Outside.service(this, "gus.x.execute.th.delay1s");
		restart = Outside.service(this, "gus06.app.restart0");
	}

	public Object t(Object obj) throws Exception
	{
		execute1s.p(restart);
		return "Restarting...";
	}
}
