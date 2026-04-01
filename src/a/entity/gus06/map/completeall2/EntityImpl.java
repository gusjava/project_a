package a.entity.gus06.map.completeall2;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20180302";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		List list = (List) o[1];
		
		perform(map,list);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		List list = (List) o[1];
		
		Map map1 = new HashMap(map);
		perform(map1,list);
		return map1;
	}
	
	
	private void perform(Map map, List list) throws Exception
	{
		List buffer = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			buffer.add(list.get(i));
			if(buffer.size()==2)
			{
				T t_key = (T) buffer.get(0);
				T t_value = (T) buffer.get(1);
				
				Object key = t_key.t(map);
				Object value = t_value.t(map);
				
				map.put(key,value);
				buffer.clear();
			}
		}
	}
}
