package a.entity.gus06.convert.maparraytor;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191122";}


	public Object t(Object obj) throws Exception
	{return new R1((Map[]) obj);}
	
	
	
		
	
	private class R1 implements R
	{
		private Map[] maps;
		public R1(Map[] maps) {this.maps = maps;}
		
		public Object r(String key) throws Exception
		{
			for(Map map : maps)
			if(map.containsKey(key)) return map.get(key);
			return null;
		}
	}
}
