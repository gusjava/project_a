package a.entity.gus06.sys.expression1.apply.op._replacer1;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160305";}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Map) return new T1((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Map map;
		public T1(Map map) {this.map = map;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return null;
			if(obj instanceof String) return replace(map,(String) obj);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	private String replace(Map m, String s)
	{
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = ""+m.get(key);
			
			s = s.replace(key,value);
		}
		return s;
	}
}
