package a.entity.gus.y.server1.engine.cmd.e.importedby;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260422";}

	private Service importedBy;
	private Service entityEngine;
	private Service joinArgs;

	public EntityImpl() throws Exception {
		importedBy   = Outside.service(this, "gus.y.entitydb1.entity.findall.importedby");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
		joinArgs     = Outside.service(this, "gus.y.server1.tool.args.fullstring");
	}

	public Object t(Object obj) throws Exception
	{return importedBy.t(new Object[]{cx(), joinArgs(obj)});}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}

	private String joinArgs(Object args) throws Exception
	{return (String) joinArgs.t(args);}
}