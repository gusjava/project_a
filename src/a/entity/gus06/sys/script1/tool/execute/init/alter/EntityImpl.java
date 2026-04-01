package a.entity.gus06.sys.script1.tool.execute.init.alter;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160223";}


	private Service replace;
	private Service get;

	public EntityImpl() throws Exception
	{
		replace = Outside.service(this,"gus06.map.deep.put.replace");
		get = Outside.service(this,"gus06.map.deep.get");
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
			Iterator it = data.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				Object value = data.get(key);
				
				Object value0 = get.t(new Object[]{context,key});
				if(value0!=null) backup.put(key,value0);
				
				replace.p(new Object[]{context,key,value});
			}
		}
		
		return new E0(context,data,backup);
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
			
			Iterator it = data.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				Object value0 = get(backup,key);
				
				replace.p(new Object[]{context,key,value0});
			}
		}
	}
}
