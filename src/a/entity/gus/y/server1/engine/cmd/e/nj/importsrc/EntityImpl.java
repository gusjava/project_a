package a.entity.gus.y.server1.engine.cmd.e.nj.importsrc;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service importSrc;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		importSrc    = Outside.service(this, "gus.y.entitysys1.perform.entity.importsrc");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		Map json = (Map) obj;
		String src = (String) json.get("src");
		boolean done = importSrc.f(new Object[]{entityEngine, src});
		return done ? "done" : "import failed";
	}
}
