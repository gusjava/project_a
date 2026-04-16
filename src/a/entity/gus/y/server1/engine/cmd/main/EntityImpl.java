package a.entity.gus.y.server1.engine.cmd.main;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service buildDesc;
	private Service joinArgs;
	private Map main;

	public EntityImpl() throws Exception
	{
		buildDesc = Outside.service(this, "gus06.tostring.desc");
		joinArgs = Outside.service(this,"gus.y.server1.tool.joinargs");
		main = (Map) Outside.resource(this, "main");
	}

	public Object t(Object obj) throws Exception
	{
		Map payload = (Map) obj;
		List args = (List) payload.get("args");
		
		if(args==null || args.isEmpty()) return buildDesc.t(main);
		
		String key = (String) joinArgs.t(args);
		return buildDesc.t(main.get(key));
	}
}
