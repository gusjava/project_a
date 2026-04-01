package a.entity.gus06.feature.cache.i;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180503";}

	
	public Object t(Object obj) throws Exception
	{
		return new Cached((I) obj);
	}
	
	private class Cached implements I
	{
		private I i;
		private Object r;
		private boolean cached = false;
		
		public Cached(I i)
		{
			this.i = i;
		}
		
		public Object i() throws Exception
		{
			if(!cached)
			{
				r = i.i();
				cached = true;
			}
			return r;
		}
	}
}
