package a.entity.gus06.convert.maptof;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151123";}


	public Object t(Object obj) throws Exception
	{return new F1((Map) obj);}
	
	
	private class F1 implements F
	{
		private Map map;
		public F1(Map map) {this.map = map;}
		
		public boolean f(Object obj) throws Exception
		{return map.containsKey(obj);}
	}
}