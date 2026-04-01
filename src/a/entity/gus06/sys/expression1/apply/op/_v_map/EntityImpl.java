package a.entity.gus06.sys.expression1.apply.op._v_map;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160212";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof V) return new P1((V) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class P1 implements P
	{
		private V v;
		
		public P1(V v) {this.v = v;}
		
		public void p(Object obj) throws Exception
		{
			Map map = (Map) obj;
			Iterator it = map.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				Object value = map.get(key);
				v.v(key,value);
			}
		}
	}
}
