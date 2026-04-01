package a.entity.gus06.map.build.fromlist.kvkv;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180302";}

	
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		Map map = new HashMap();
		
		List buff = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			buff.add(list.get(i));
			if(buff.size()==2)
			{
				map.put(buff.get(0),buff.get(1));
				buff.clear();
			}
		}
		return map;
	}
}
