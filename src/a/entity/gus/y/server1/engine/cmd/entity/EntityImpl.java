package a.entity.gus.y.server1.engine.cmd.entity;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service uniqueentity;

	public EntityImpl() throws Exception
	{
		uniqueentity = Outside.service(this, "uniqueentity");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length != 2) throw new Exception("Wrong data number: " + o.length);
		String entityName = (String) o[0];
		Object rawArgs = o[1];
		Object entity = uniqueentity.t(entityName);
		if(rawArgs == null) return ((G) entity).g();
		if(!(rawArgs instanceof List)) return ((T) entity).t(rawArgs);
		List args = (List) rawArgs;
		if(args.size() == 1) return ((T) entity).t(args.get(0));
		return ((T) entity).t(args);
	}
}
