package a.entity.gus06.feature.cache.r;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180503";}

	
	public Object t(Object obj) throws Exception
	{
		return new Cached((R) obj);
	}
	
	private class Cached implements R
	{
		private R r;
		private Map cache;
		
		public Cached(R r)
		{
			this.r = r;
			cache = new HashMap();
		}
		
		public Object r(String key) throws Exception
		{
			if(!cache.containsKey(key))
			cache.put(key,r.r(key));
			
			return cache.get(key);
		}
	}
}
