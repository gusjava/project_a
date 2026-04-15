package a.entity.gus.y.server1.engine.cmd.e.findall_creationdate_co;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service findAllCreationDateCo;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		findAllCreationDateCo = Outside.service(this, "gus.y.entitydb1.entity.findall.creationdate.asmap.co");
		entityEngine          = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{return findAllCreationDateCo.t(new Object[]{cx(), joinArgs(obj)});}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}

	private String joinArgs(Object args) throws Exception
	{
		if(args instanceof String) return (String) args;
		if(args instanceof List) {
			List list = (List) args;
			StringBuilder sb = new StringBuilder((String) list.get(0));
			for(int i=1; i<list.size(); i++) sb.append(" ").append(list.get(i));
			return sb.toString();
		}
		throw new Exception("Invalid args type: "+args.getClass().getSimpleName());
	}
}
