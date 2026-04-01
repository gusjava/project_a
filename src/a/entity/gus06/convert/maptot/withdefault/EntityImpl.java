package a.entity.gus06.convert.maptot.withdefault;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170321";}


	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return new T1((Map) obj, null);
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			return new T1((Map) o[0], o[1]);
		}
		if(obj instanceof List)
		{
			List list = (List) obj;
			if(list.size()!=2) throw new Exception("Wrong data number: "+list.size());
			return new T1((Map) list.get(0), list.get(1));
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
		
	
	private class T1 implements T
	{
		private Map map;
		private Object defaultValue;
		
		public T1(Map map, Object defaultValue)
		{
			this.map = map;
			this.defaultValue = defaultValue;
		}
		
		public Object t(Object obj) throws Exception
		{
			if(!map.containsKey(obj)) return defaultValue;
			return map.get(obj);
		}
	}
}
