package a.entity.gus06.feature.cache.f;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180503";}

	
	public Object t(Object obj) throws Exception
	{
		return new Cached((F) obj);
	}
	
	private class Cached implements F
	{
		private F f;
		private Map cache;
		
		public Cached(F f)
		{
			this.f = f;
			cache = new HashMap();
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(!cache.containsKey(obj))
			cache.put(obj,Boolean.valueOf(f.f(obj)));
			
			return ((Boolean) cache.get(obj)).booleanValue();
		}
	}
}