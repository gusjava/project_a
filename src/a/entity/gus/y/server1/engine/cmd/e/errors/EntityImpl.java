package a.entity.gus.y.server1.engine.cmd.e.errors;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service findCompileErrors;
	private Service findCompileErrorsAll;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		findCompileErrors    = Outside.service(this, "gus.y.entitydb1.entity_compile_err.infos.w_name");
		findCompileErrorsAll = Outside.service(this, "gus.y.entitydb1.entity_compile_err.infosbyname");
		entityEngine         = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list != null && !list.isEmpty()) return findCompileErrors.t(new Object[]{cx(), joinArgs(list)});
		return findCompileErrorsAll.t(cx());
	}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}

	private String joinArgs(List args)
	{
		StringBuilder sb = new StringBuilder((String) args.get(0));
		for(int i=1; i<args.size(); i++) sb.append(" ").append(args.get(i));
		return sb.toString();
	}
}
