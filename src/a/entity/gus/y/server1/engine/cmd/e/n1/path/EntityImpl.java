package a.entity.gus.y.server1.engine.cmd.e.n1.path;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service joinArgs;
	private Service findMainFile;
	private Service entityEngine;

	public EntityImpl() throws Exception
	{
		joinArgs     = Outside.service(this, "gus.y.server1.tool.args.fullstring");
		findMainFile = Outside.service(this, "gus.y.entitysys1.find.mainfile");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		String name = joinArgs(obj);
		Object file = findMainFile.t(new Object[]{entityEngine, name});
		if(file == null) throw new Exception("Entity not found: " + name);
		return file.toString();
	}

	private String joinArgs(Object args) throws Exception
	{return (String) joinArgs.t(args);}
}
