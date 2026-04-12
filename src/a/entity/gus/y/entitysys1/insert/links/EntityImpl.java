package a.entity.gus.y.entitysys1.insert.links;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240121";}
	
	public static final String KEY_ENTITY_NAME = "entity_name";
	public static final String KEY_LINKS = "links";
	
	private Service insert;

	public EntityImpl() throws Exception {
		insert = Outside.service(this, "gus.y.entitydb1.entity_link.insert");
	}

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map entityMap = (Map) o[1];

		String entityName = (String) entityMap.get(KEY_ENTITY_NAME);
		Set linkInfos = (Set) entityMap.get(KEY_LINKS);

		Iterator it = linkInfos.iterator();
		Set done = new HashSet();
		while (it.hasNext()) {
			String linkInfo = (String) it.next();
			String link = linkInfo.split(":",2)[1];
			if(done.contains(link)) continue;
			
			insert.p(new Object[] {cx, entityName, link});
			done.add(link);
		}
	}
}
