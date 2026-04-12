package a.entity.gus.y.searchclass1.find;

import a.framework.*;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240121";}

	private Service classPaths1;
	private Map map;

	public EntityImpl() throws Exception {
		classPaths1 = Outside.service(this, "gus.x.java.classpaths1");
		map = (Map) classPaths1.g();
	}
	
	public Object t(Object obj) throws Exception {
		String query = (String) obj;
		List list1 = new ArrayList();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			if(key.startsWith(query)) {
				List list = (List) map.get(key);
				list1.addAll(list);
			}
		}
		return list1;
	}
}
