package a.entity.gus06.convert.maptot.withdefault.id;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190325";}


	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return new T1((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
		
	
	private class T1 implements T
	{
		private Map map;
		public T1(Map map) {this.map = map;}
		
		public Object t(Object obj) throws Exception
		{
			if(!map.containsKey(obj)) return obj;
			return map.get(obj);
		}
	}
}
