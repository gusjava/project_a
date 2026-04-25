package a.entity.gus.y.server1.engine.cmd.e.nr.createtree;

import java.util.ArrayList;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service entityCreateTree;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		entityCreateTree = Outside.service(this, "gus.y.entitysys1.perform.entity.createtree");
		entityEngine     = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		if(obj == null) throw new Exception("Usage: e-createtree :<json>");
		List tree = parseTree((List) obj);
		boolean done = (Boolean) entityCreateTree.f(new Object[]{entityEngine, tree});
		return done ? "done" : "createtree failed (entity already exists or invalid name)";
	}

	private List parseTree(List jsonTree) throws Exception
	{
		List result = new ArrayList();
		for(int i=0; i<jsonTree.size(); i++) {
			List node = (List) jsonTree.get(i);
			String desc = (String) node.get(0);
			List children = parseTree((List) node.get(1));
			result.add(new Object[]{desc, children});
		}
		return result;
	}
}
