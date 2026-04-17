package a.entity.gus.y.server1.tool.payload.args.fullstring;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service joinArgs;

	public EntityImpl() throws Exception
	{
		joinArgs = Outside.service(this,"gus.y.server1.tool.args.fullstring");
	}

	public Object t(Object obj) throws Exception
	{
		Map payload = (Map) obj;
		List args = (List) payload.get("args");
		if(args==null || args.isEmpty()) return null;
		return joinArgs.t(args);
	}
}
