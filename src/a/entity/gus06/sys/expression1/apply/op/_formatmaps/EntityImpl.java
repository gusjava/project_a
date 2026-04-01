package a.entity.gus06.sys.expression1.apply.op._formatmaps;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160820";}


	private Service formatter;
	
	public EntityImpl() throws Exception
	{
		formatter = Outside.service(this,"gus06.map.string.formatter1");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value instanceof List) return new T1(value);
		if(value instanceof Set) return new T1(value);
		if(value instanceof Map) return new T1(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object value;
		
		public T1(Object value)
		{this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			return formatMaps(value,s);
		}
	}
	
	
	private Object formatMaps(Object value, String s) throws Exception
	{
		if(value instanceof List)
		{
			List input = (List) value;
			List output = new ArrayList();
			for(Object elem:input)
			{
				Object elem1 = formatter.t(new Object[]{elem,s});
				output.add(elem1);
			}
			return output;
		}
		
		if(value instanceof Set)
		{
			Set input = (Set) value;
			Set output = new HashSet();
			for(Object elem:input)
			{
				Object elem1 = formatter.t(new Object[]{elem,s});
				output.add(elem1);
			}
			return output;
		}
		
		if(value instanceof Map)
		{
			Map input = (Map) value;
			Map output = new HashMap();
			Iterator it = input.keySet().iterator();
			while(it.hasNext())
			{
				Object key = it.next();
				Object elem = input.get(key);
				Object elem1 = formatter.t(new Object[]{elem,s});
				output.put(key,elem1);
			}
			return output;
		}
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}
