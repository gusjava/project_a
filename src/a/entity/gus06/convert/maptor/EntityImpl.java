package a.entity.gus06.convert.maptor;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151124";}


	public Object t(Object obj) throws Exception
	{return new R1((Map) obj);}
	
	
	
		
	
	private class R1 implements R
	{
		private Map map;
		public R1(Map map) {this.map = map;}
		
		public Object r(String key) throws Exception
		{
			if(!map.containsKey(key)) return null;
			return map.get(key);
		}
	}
}
