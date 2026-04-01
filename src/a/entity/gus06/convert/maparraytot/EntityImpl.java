package a.entity.gus06.convert.maparraytot;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191122";}


	public Object t(Object obj) throws Exception
	{return new T1((Map[]) obj);}
	
	
	
		
	
	private class T1 implements T
	{
		private Map[] maps;
		public T1(Map[] maps) {this.maps = maps;}
		
		public Object t(Object obj) throws Exception
		{
			for(Map map : maps)
			if(map.containsKey(obj)) return map.get(obj);
			return null;
		}
	}
}
