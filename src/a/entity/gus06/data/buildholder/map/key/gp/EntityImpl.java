package a.entity.gus06.data.buildholder.map.key.gp;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191113";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		
		return new Holder(map,key);
	}

	
	
	
	private class Holder implements P, G
	{
		private Map map;
		private Object key;
		
		public Holder(Map map, Object key)
		{
			this.map = map;
			this.key = key;
		}
		
		public Object g() throws Exception
		{return map.containsKey(key) ? map.get(key) : null;}
		
		public void p(Object obj) throws Exception
		{map.put(key,obj);}
	}
}
