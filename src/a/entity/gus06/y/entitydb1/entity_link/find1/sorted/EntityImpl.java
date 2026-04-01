package a.entity.gus06.y.entitydb1.entity_link.find1.sorted;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251113";}

	private Service find;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this, "gus.y.entitydb1.entity_link.find1");
	}

	public Object t(Object obj) throws Exception
	{
		List list = new ArrayList((Set) find.t(obj));
		Collections.sort(list);
		return list;
	}
}