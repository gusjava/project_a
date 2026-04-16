package a.entity.gus.y.server1.engine.cmd.e.srcpart;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service findSrcPart;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		findSrcPart  = Outside.service(this, "gus.y.entitysys1.find.srcpart");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size()!=3)
			throw new Exception("Usage: e-srcpart <entity> <start> <end>");
		
		String name = (String) list.get(0);
		Integer start = Integer.parseInt(""+list.get(1));
		Integer end = Integer.parseInt(""+list.get(2));
		
		int[] range = new int[]{start.intValue(), end.intValue()};
		Object src = findSrcPart.t(new Object[]{entityEngine, name, range});
		if(src == null) throw new Exception("Entity not found: " + name);
		return src;
	}
}
