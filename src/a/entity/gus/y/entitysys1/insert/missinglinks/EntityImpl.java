package a.entity.gus.y.entitysys1.insert.missinglinks;

import java.sql.Connection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240126";}

	public static final String KEY_ENTITY_NAME = "entity_name";
	public static final String KEY_MISSING_LINKS = "missing_links";
	
	private Service insert;
	
	public EntityImpl() throws Exception {
		insert = Outside.service(this, "gus.y.entitydb1.entity_missing_link.insert");
	}

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String entityName = (String) data.get(KEY_ENTITY_NAME);
		Set missingLinks = (Set) data.get(KEY_MISSING_LINKS);

		Iterator it = missingLinks.iterator();
		while (it.hasNext()) {
			String missingInfo = (String) it.next();
			String[] n = missingInfo.split(":",2);
			Integer pos = Integer.parseInt(n[0]);
			String missingLink = n[1];
			insert.p(new Object[] {cx, entityName, missingLink, pos});
		}
	}
	
}
