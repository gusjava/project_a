package a.entity.gus06.y.entitysys1.insert.imports;

import java.sql.Connection;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251215";}

	public static final String KEY_ENTITY_NAME = "entity_name";
	public static final String KEY_IMPORTS = "imports";
	
	private Service insert;
	
	public EntityImpl() throws Exception
	{
		insert = Outside.service(this,"gus06.y.entitydb1.entity_import.insert");
	}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String entityName = (String) data.get(KEY_ENTITY_NAME);
		Set importInfos = (Set) data.get(KEY_IMPORTS);

		Iterator it = importInfos.iterator();
		Set done = new HashSet();
		while (it.hasNext())
		{
			String import_ = (String) it.next();
			if(done.contains(import_)) continue;
			
			insert.p(new Object[] {cx, entityName, import_});
			done.add(import_);
		}
	}
}
