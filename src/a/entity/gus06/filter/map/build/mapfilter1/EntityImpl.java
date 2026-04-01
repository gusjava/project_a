package a.entity.gus06.filter.map.build.mapfilter1;

import java.util.Iterator;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160816";}


	public Object t(Object obj) throws Exception
	{return new F1((Map) obj);}



	private class F1 implements F
	{
		private Map map;
		public F1(Map map) {this.map = map;}
		
		public boolean f(Object obj) throws Exception
		{
			Map m = (Map) obj;
			if(m.size()!=map.size()) return false;
			
			Iterator it = m.keySet().iterator();
			while(it.hasNext())
			{
				Object key = it.next();
				if(!map.containsKey(key)) return false;
				
				F f = (F) map.get(key);
				Object value = m.get(key);
				if(!f.f(value)) return false;
			}
			return true;
		}
	}
}
