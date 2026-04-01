package a.entity.gus06.feature.cache.t;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180503";}

	
	public Object t(Object obj) throws Exception
	{
		return new Cached((T) obj);
	}
	
	private class Cached implements T
	{
		private T t;
		private Map cache;
		
		public Cached(T t)
		{
			this.t = t;
			cache = new HashMap();
		}
		
		public Object t(Object obj) throws Exception
		{
			if(!cache.containsKey(obj))
			cache.put(obj,t.t(obj));
			
			return cache.get(obj);
		}
	}
}
