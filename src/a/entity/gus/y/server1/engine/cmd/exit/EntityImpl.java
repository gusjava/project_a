package a.entity.gus.y.server1.engine.cmd.exit;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service execute1s;
	private Service exit;

	public EntityImpl() throws Exception
	{
		execute1s = Outside.service(this, "gus.x.execute.th.delay1s");
		exit = Outside.service(this, "gus.y.app1.execute.exit");
	}

	public Object t(Object obj) throws Exception
	{
		execute1s.p(exit);
		return "Bye.";
	}
}
