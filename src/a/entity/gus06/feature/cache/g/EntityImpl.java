package a.entity.gus06.feature.cache.g;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180503";}

	
	public Object t(Object obj) throws Exception
	{
		return new Cached((G) obj);
	}
	
	private class Cached implements G
	{
		private G g;
		private Object r;
		private boolean cached = false;
		
		public Cached(G g)
		{
			this.g = g;
		}
		
		public Object g() throws Exception
		{
			if(!cached)
			{
				r = g.g();
				cached = true;
			}
			return r;
		}
	}
}
