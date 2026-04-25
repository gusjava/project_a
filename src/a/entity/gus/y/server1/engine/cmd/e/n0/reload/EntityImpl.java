package a.entity.gus.y.server1.engine.cmd.e.n0.reload;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service entityEngine;

	public EntityImpl() throws Exception
	{
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		entityEngine.e();
		Map map = (Map) entityEngine.r("compileErrMap");
		if(map.size()>0) return map;
		
		return "complete without compile errors";
	}
}
