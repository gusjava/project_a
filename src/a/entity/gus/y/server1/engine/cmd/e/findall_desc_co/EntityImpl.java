package a.entity.gus.y.server1.engine.cmd.e.findall_desc_co;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service findAllDescCo;
	private Service entityEngine;
	private Service joinArgs;

	public EntityImpl() throws Exception {
		findAllDescCo = Outside.service(this, "gus.y.entitydb1.entity.findall.desc.co");
		entityEngine  = Outside.service(this, "gus.y.entitysys1.engine");
		joinArgs      = Outside.service(this, "gus.y.server1.tool.joinargs");
	}

	public Object t(Object obj) throws Exception
	{return findAllDescCo.t(new Object[]{cx(), joinArgs(obj)});}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}

	private String joinArgs(Object args) throws Exception
	{return (String) joinArgs.t(args);}
}
