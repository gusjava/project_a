package a.entity.gus.y.server1.engine.cmd.e.features;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service findFeatures;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		findFeatures = Outside.service(this, "gus.y.entitydb1.entity.find.features");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-features <entity>");
		String name = (String) list.get(0);
		String features = (String) findFeatures.t(new Object[]{cx(), name});
		if(features == null) throw new Exception("Entity not found: " + name);
		return features.toUpperCase();
	}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}
}
