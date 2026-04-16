package a.entity.gus.y.server1.engine.cmd.resource;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service buildDesc;
	private Service joinArgs;

	public EntityImpl() throws Exception
	{
		buildDesc = Outside.service(this, "gus06.tostring.desc");
		joinArgs = Outside.service(this,"gus.y.server1.tool.joinargs");
	}

	public Object t(Object obj) throws Exception
	{
		Map payload = (Map) obj;
		List args = (List) payload.get("args");
		String rule = (String) joinArgs.t(args);
		return buildDesc.t(Outside.resource(this, rule));
	}
}
