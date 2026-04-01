package a.entity.gus06.feature.wrap.gmap.g;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		Map map = (Map) o[1];
		
		return new Wrap(g,map);
	}
	
	
	
	
	
	private class Wrap implements G
	{
		private G g;
		private Map map;
		
		public Wrap(G g, Map map)
		{
			this.g = g;
			this.map = map;
		}
		
		public Object g() throws Exception
		{
			Object obj = g.g();
			return map.containsKey(obj) ? map.get(obj) : null;
		}
	}
}
