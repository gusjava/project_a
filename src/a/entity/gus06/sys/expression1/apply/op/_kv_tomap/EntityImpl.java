package a.entity.gus06.sys.expression1.apply.op._kv_tomap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180221";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		return new T1(obj);
	}
	
	private class T1 implements T
	{
		private Object key;
		public T1(Object key) {this.key = key;}
		
		public Object t(Object obj) throws Exception
		{
			Map map = new HashMap();
			map.put(key,obj);
			return map;
		}
	}
}
