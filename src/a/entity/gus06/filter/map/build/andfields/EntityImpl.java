package a.entity.gus06.filter.map.build.andfields;

import java.util.Iterator;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}


	public Object t(Object obj) throws Exception
	{return new FAndMap((Map)obj);}



	private class FAndMap implements F
	{
		private Map map;
		public FAndMap(Map map) {this.map = map;}
		
		public boolean f(Object obj) throws Exception
		{
			Map m = (Map) obj;
			Iterator it = m.keySet().iterator();
			while(it.hasNext())
			{
				Object key = it.next();
				if(map.containsKey(key))
				{
					F f = (F) map.get(key);
					Object value = m.get(key);
					if(!f.f(value)) return false;
				}
			}
			return true;
		}
	}
}
