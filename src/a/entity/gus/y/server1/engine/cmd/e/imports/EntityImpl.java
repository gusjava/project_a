package a.entity.gus.y.server1.engine.cmd.e.imports;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260422";}

	private Service imports;
	private Service entityEngine;
	private Service joinArgs;

	public EntityImpl() throws Exception {
		imports      = Outside.service(this, "gus.y.entitydb1.entity_import.imports.w_name");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
		joinArgs     = Outside.service(this, "gus.y.server1.tool.args.fullstring");
	}

	public Object t(Object obj) throws Exception
	{return imports.t(new Object[]{cx(), joinArgs(obj)});}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}

	private String joinArgs(Object args) throws Exception
	{return (String) joinArgs.t(args);}
}