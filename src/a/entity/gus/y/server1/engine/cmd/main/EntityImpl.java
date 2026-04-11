package a.entity.gus.y.server1.engine.cmd.main;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service buildDesc;
	private Map main;

	public EntityImpl() throws Exception
	{
		buildDesc = Outside.service(this, "gus06.tostring.desc");
		main = (Map) Outside.resource(this, "main");
	}

	public Object t(Object obj) throws Exception
	{
		List args = (List) obj;
		if(args.isEmpty()) return buildDesc.t(main);
		String key = (String) args.get(0);
		return buildDesc.t(main.get(key));
	}
}
