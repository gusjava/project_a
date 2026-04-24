package a.entity.gus.y.server1.engine.cmd.e.n1.computehash;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260419";}

	private Service findSrc;
	private Service entityEngine;
	private Service buildHash;
	private Service joinArgs;

	public EntityImpl() throws Exception {
		findSrc      = Outside.service(this, "gus.y.entitysys1.find.src");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
		buildHash     = Outside.service(this, "gus.y.entityhash1.src.build");
		joinArgs     = Outside.service(this, "gus.y.server1.tool.args.fullstring");
	}

	public Object t(Object obj) throws Exception
	{
		String name = joinArgs(obj);
		Object src = findSrc.t(new Object[]{entityEngine, name});
		if(src == null) throw new Exception("Entity not found: " + name);
		return buildHash.t(src);
	}

	private String joinArgs(Object args) throws Exception
	{return (String) joinArgs.t(args);}
}
