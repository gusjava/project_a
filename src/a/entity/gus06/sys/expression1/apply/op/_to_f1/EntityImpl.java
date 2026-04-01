package a.entity.gus06.sys.expression1.apply.op._to_f1;

import a.framework.*;
import java.util.Map;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160816";}


	private Service builder1;
	private Service builder2;
	
	public EntityImpl() throws Exception
	{
		builder1 = Outside.service(this,"gus06.sys.expression1.builder2.f");
		builder2 = Outside.service(this,"gus06.filter.map.build.mapfilter1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof F) return value;
		
		Map fmap = toFMap(value,opMap);
		return builder2.t(fmap);
	}
	
	
	
	private Map toFMap(Object obj, Map opMap) throws Exception
	{
		if(obj instanceof Map)
		{
			Map m = new HashMap();
			Map map = (Map) obj;
			
			Iterator it = map.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				Object value = map.get(key);
				F filter = (F) builder1.t(new Object[]{value,opMap});
				m.put(key,filter);
			}
			return m;
		}
		
		if(obj instanceof Collection)
		{
			Map m = new HashMap();
			Collection c = (Collection) obj;
			
			Iterator it = c.iterator();
			while(it.hasNext()) m.put(it.next(),new F0());
			return m;
		}
		
		if(obj instanceof String[])
		{
			Map m = new HashMap();
			String[] ss = (String[]) obj;
			
			for(String s:ss) m.put(s,new F0());
			return m;
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class F0 implements F
	{
		public boolean f(Object obj) throws Exception
		{return true;}
	}
}
