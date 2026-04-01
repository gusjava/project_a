package a.entity.gus06.map.build.list.keys;

import a.framework.*;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191022";}
	

	public Object t(Object obj) throws Exception
	{
		Map m = (Map) obj;
		return new ArrayList(m.keySet());
	}
}
