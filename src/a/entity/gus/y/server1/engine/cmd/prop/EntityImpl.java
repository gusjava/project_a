package a.entity.gus.y.server1.engine.cmd.prop;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service getArg;
	private Map prop;

	public EntityImpl() throws Exception
	{
		getArg = Outside.service(this,"gus.y.server1.tool.payload.args.fullstring");
		prop = (Map) Outside.resource(this, "props");
	}

	public Object t(Object obj) throws Exception
	{
		String arg = (String) getArg.t(obj);
		return arg!=null ? prop.get(arg) : prop;
	}
}