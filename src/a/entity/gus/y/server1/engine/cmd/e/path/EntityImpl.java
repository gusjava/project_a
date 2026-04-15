package a.entity.gus.y.server1.engine.cmd.e.path;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service findMainFile;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		findMainFile = Outside.service(this, "gus.y.entitysys1.find.mainfile");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-path <entity>");
		String name = (String) list.get(0);
		Object file = findMainFile.t(new Object[]{entityEngine, name});
		if(file == null) throw new Exception("Entity not found: " + name);
		return file.toString();
	}
}
