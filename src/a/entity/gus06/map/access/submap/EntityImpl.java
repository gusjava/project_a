package a.entity.gus06.map.access.submap;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200315";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		String key0 = (String) o[1];
		return new Holder(map,key0);
	}

	
	private class Holder implements V, R
	{	
		private Map map;
		private String key0;
		
		public Holder(Map map, String key0)
		{
			this.map = map;
			this.key0 = key0;
		}
		
		public void v(String key, Object obj) throws Exception
		{map.put(key0+key,obj);}
		
		public Object r(String key) throws Exception
		{return map.containsKey(key0+key) ? map.get(key0+key) : null;}
	}
}
