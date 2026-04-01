package a.entity.gus06.map.access;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200315";}

	
	
	public Object t(Object obj) throws Exception
	{return new Holder((Map)obj);}

	
	
	private class Holder implements V, R
	{	
		private Map map;
		public Holder(Map map){this.map = map;}
		
		public void v(String key, Object obj) throws Exception
		{map.put(key,obj);}
		
		public Object r(String key) throws Exception
		{return map.containsKey(key) ? map.get(key) : null;}
	}
}
