package a.entity.gus06.convert.maptot;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151118";}


	public Object t(Object obj) throws Exception
	{return new T1((Map) obj);}
	
	
	
		
	
	private class T1 implements T
	{
		private Map map;
		public T1(Map map) {this.map = map;}
		
		public Object t(Object obj) throws Exception
		{
			if(!map.containsKey(obj)) return null;
			return map.get(obj);
		}
	}
}
