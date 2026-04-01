package a.entity.gus06.feature.wrap.fmapmap.t;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161211";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		Map map1 = (Map) o[1];
		Map map2 = (Map) o[2];
		
		return new Wrap(f,map1,map2);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private F f;
		private Map map1;
		private Map map2;
		
		public Wrap(F f, Map map1, Map map2)
		{
			this.f = f;
			this.map1 = map1;
			this.map2 = map2;
		}
		
		public Object t(Object obj) throws Exception
		{
			Map map = f.f(obj) ? map1 : map2;
			return  map!=null ? get(map,obj) : null;
		}
		
		private Object get(Map map, Object key)
		{return map.containsKey(key) ? map.get(key) : null;}
	}
}
