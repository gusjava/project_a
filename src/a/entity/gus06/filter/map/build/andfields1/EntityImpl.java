package a.entity.gus06.filter.map.build.andfields1;

import java.util.Iterator;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}
	
	public static final String KEY_ALL = "<ALL>";
	public static final String KEY_ONE = "<ONE>";



	public Object t(Object obj) throws Exception
	{return new FAndMap((Map)obj);}



	private class FAndMap implements F
	{
		private Map map;
		private F f_all;
		private F f_one;
		
		public FAndMap(Map map)
		{
			this.map = map;
			if(map.containsKey(KEY_ALL)) f_all = (F) map.get(KEY_ALL);
			if(map.containsKey(KEY_ONE)) f_one = (F) map.get(KEY_ONE);
		}
		
		public boolean f(Object obj) throws Exception
		{
			Map m = (Map) obj;
			if(f_all!=null) return apply_all(m);
			if(f_one!=null) return apply_one(m);
			return apply_map(m);
		}
		
		
		
		
		private boolean apply_all(Map m) throws Exception
		{
			Iterator it = m.keySet().iterator();
			while(it.hasNext())
			{
				Object key = it.next();
				Object value = m.get(key);
				if(!f_all.f(value)) return false;
			}
			return true;
		}
		
		private boolean apply_one(Map m) throws Exception
		{
			Iterator it = m.keySet().iterator();
			while(it.hasNext())
			{
				Object key = it.next();
				Object value = m.get(key);
				if(f_one.f(value)) return true;
			}
			return false;
		}
		
		private boolean apply_map(Map m) throws Exception
		{
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
