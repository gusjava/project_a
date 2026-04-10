package a.entity.gus.z.server1.engine.cmd.resource;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service buildDesc;

	public EntityImpl() throws Exception
	{
		buildDesc = Outside.service(this, "gus06.tostring.desc");
	}

	public Object t(Object obj) throws Exception
	{
		List args = (List) obj;
		String rule = String.join(" ", args);
		return buildDesc.t(Outside.resource(this, rule));
	}
}
