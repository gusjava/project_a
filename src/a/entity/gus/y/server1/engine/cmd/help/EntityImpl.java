package a.entity.gus.y.server1.engine.cmd.help;

import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service helpMap;
	private Service getArg;

	public EntityImpl() throws Exception
	{
		helpMap = Outside.service(this,"gus.y.server1.tool.helpmap");
		getArg = Outside.service(this,"gus.y.server1.tool.payload.args.fullstring");
	}

	public Object t(Object obj) throws Exception
	{
		String arg = (String) getArg.t(obj);
		Map help = (Map) helpMap.g();
		return arg!=null ? help.get(arg) : help;
	}
}
