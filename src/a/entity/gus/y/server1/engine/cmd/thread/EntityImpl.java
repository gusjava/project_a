package a.entity.gus.y.server1.engine.cmd.thread;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260419";}

	private Service getArg;
	private Service buildInfo;

	public EntityImpl() throws Exception
	{
		getArg = Outside.service(this,"gus.y.server1.tool.payload.args.fullstring");
		buildInfo = Outside.service(this, "gus.x.thread.build.info");
	}

	public Object t(Object obj) throws Exception
	{
		String arg = (String) getArg.t(obj);
		if(arg.equals("EDT")) return buildInfo.g();
		return buildInfo.t(arg);
	}
}
