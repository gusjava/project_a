package a.entity.gus06.sys.store.t.map.data.stringlist;

import a.framework.*;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140908";}

	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		ArrayList list = new ArrayList();
		
		int index = 0;
		while(map.containsKey("element-"+index))
		{
			list.add(map.get("element-"+index));
			index++;
		}
		return list;
	}
}
