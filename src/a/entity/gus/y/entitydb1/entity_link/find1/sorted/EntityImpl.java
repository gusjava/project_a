package a.entity.gus.y.entitydb1.entity_link.find1.sorted;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240112";}

	private Service find;

	public EntityImpl() throws Exception {
		find = Outside.service(this, "gus.y.entitydb1.entity_link.find1");
	}

	public Object t(Object obj) throws Exception {
		List list = new ArrayList((Set) find.t(obj));
		Collections.sort(list);
		return list;
	}
}
