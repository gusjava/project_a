package a.entity.gus06.feature.wrap.fmap.t;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		Map map = (Map) o[1];
		
		return new Wrap(f,map);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private F f;
		private Map map;
		
		public Wrap(F f, Map map)
		{
			this.f = f;
			this.map = map;
		}
		
		public Object t(Object obj) throws Exception
		{
			return f.f(obj) ? get(obj) : obj;
		}
		
		private Object get(Object key)
		{return map.containsKey(key) ? map.get(key) : null;}
	}
}
