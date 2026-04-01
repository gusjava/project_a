package a.entity.gus06.feature.wrap.tmap.t;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		Map map = (Map) o[1];
		
		return new Wrap(t,map);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private T t;
		private Map map;
		
		public Wrap(T t, Map map)
		{
			this.t = t;
			this.map = map;
		}
		
		public Object t(Object obj) throws Exception
		{
			obj = t.t(obj);
			return map.containsKey(obj) ? map.get(obj) : null;
		}
	}
}
