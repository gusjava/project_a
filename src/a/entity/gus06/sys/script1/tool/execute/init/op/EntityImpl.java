package a.entity.gus06.sys.script1.tool.execute.init.op;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160223";}


	private Service getOpMap;

	public EntityImpl() throws Exception
	{
		getOpMap = Outside.service(this,"gus06.sys.script1.access.context.opmap");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map[] o = (Map[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map context = o[0];
		Map data = o[1];
		
		Map backup = new HashMap();
		if(data!=null)
		{
			Map opMap = (Map) getOpMap.t(context);
			Map opMap1 = new HashMap();
			
			Iterator it = data.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				Object value = data.get(key);
				T op = findOp(value,opMap);
				
				T op0 = (T) get(opMap,key);
				if(op0!=null) backup.put(key,op0);
				
				opMap1.put(key,op);
			}
			opMap.putAll(opMap1);
		}
		
		return new E0(context,data,backup);
	}
	
	
	
	
	private T findOp(Object value, Map opMap) throws Exception
	{
		if(value instanceof String)
		{
			if(!opMap.containsKey(value))
				throw new Exception("Invalid operator id: "+value);
			return (T) opMap.get(value);
		}
		if(value instanceof T)
		{
			return new Operator((T) value);
		}
		throw new Exception("Invalid value type: "+value);
	}
	
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	
	private class E0 implements E
	{
		private Map context;
		private Map data;
		private Map backup;
		
		public E0(Map context, Map data, Map backup)
		{
			this.context = context;
			this.data = data;
			this.backup = backup;
		}
		
		public void e() throws Exception
		{
			if(data==null) return;
			
			Map opMap = (Map) getOpMap.t(context);
			Iterator it = data.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				T op0 = (T) get(backup,key);
				
				if(op0!=null) opMap.put(key,op0);
				else opMap.remove(key);
			}
		}
	}
	
	
	private class Operator implements T
	{
		private T t;
		public Operator(T t) {this.t = t;}
		
		public Object t(Object obj) throws Exception
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			
			return t.t(o[0]);
		}
	}
}
