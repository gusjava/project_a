package a.entity.gus06.y.entitysys1.insert.services;

import java.sql.Connection;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251111";}

	public static final String KEY_ENTITY_NAME = "entity_name";
	public static final String KEY_SERVICES = "services";

	private Service insert;
	
	public EntityImpl() throws Exception
	{
		insert = Outside.service(this, "gus.y.entitydb1.entity_service.insert");
	}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String entityName = (String) data.get(KEY_ENTITY_NAME);
		Set serviceInfos = (Set) data.get(KEY_SERVICES);

		Iterator it = serviceInfos.iterator();
		Set done = new HashSet();
		while (it.hasNext())
		{
			String callInfo = (String) it.next();
			String call = callInfo.split(":",2)[1];
			if(done.contains(call)) continue;
			
			insert.p(new Object[] {cx, entityName, call});
			done.add(call);
		}
	}
}
