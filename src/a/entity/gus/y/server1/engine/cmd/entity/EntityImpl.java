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
		List args = (List) o[1];
		T entity = (T) uniqueentity.t(entityName);
		if(args.size() == 1) return entity.t(args.get(0));
		return entity.t(args);
	}
}
