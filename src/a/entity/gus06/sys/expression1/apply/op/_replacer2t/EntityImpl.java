package a.entity.gus06.sys.expression1.apply.op._replacer2t;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180215";}



	private Service builder;
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.expression1.builder2.t");
		perform = Outside.service(this,"gus06.data.string.replacer2.t");
	}
	


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof Map) return new T1((Map) obj, opMap);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Map tmap;
		
		public T1(Map map, Map opMap) throws Exception
		{
			tmap = new HashMap();
			
			Iterator it = map.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				Object value = map.get(key);
				
				tmap.put(key,toT(value,opMap));
			}
		}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return null;
			if(obj instanceof String) return replace(tmap,(String) obj);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
		
		private T toT(Object obj, Map opMap) throws Exception
		{return (T) builder.t(new Object[]{obj,opMap});}
	}
	
	
	
	private String replace(Map m, String s) throws Exception
	{
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			T t = (T) m.get(key);
			
			s = (String) perform.t(new Object[]{s,key,t});
		}
		return s;
	}
}
