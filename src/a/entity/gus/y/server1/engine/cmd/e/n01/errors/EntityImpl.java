package a.entity.gus.y.server1.engine.cmd.e.n01.errors;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service joinArgs;
	private Service findCompileErrors;
	private Service findCompileErrorsAll;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		joinArgs     = Outside.service(this, "gus.y.server1.tool.args.fullstring");
		findCompileErrors    = Outside.service(this, "gus.y.entitydb1.entity_compile_err.infos.w_name");
		findCompileErrorsAll = Outside.service(this, "gus.y.entitydb1.entity_compile_err.infosbyname");
		entityEngine         = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list != null && !list.isEmpty())
			return findCompileErrors.t(new Object[]{cx(), joinArgs(list)});
		return findCompileErrorsAll.t(cx());
	}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}

	private String joinArgs(Object args) throws Exception
	{return (String) joinArgs.t(args);}
}
