package a.entity.gus06.sys.parser3.tool.resolve;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200326";}
	
	public static final int LOOP_LIMIT = 1000;

	private Service evalWith;
	private Service getVars;
	
	public EntityImpl() throws Exception
	{
		evalWith = Outside.service(this,"gus06.sys.parser3.tool.evalwith");
		getVars = Outside.service(this,"gus06.sys.parser3.tool.getvars");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map defMap = (Map) o[0];
		Map opMap = (Map) o[1];
		
		Resolver res = new Resolver(opMap,defMap);
		resolveMap(0,res,defMap,res.globalData);
		return res.globalData;
	}
	
	
	
	private void resolveMap(int c, Resolver res, Map def, Map data) throws Exception
	{
		if(c>LOOP_LIMIT) throw new Exception("Loop limit exceeded: "+c);
		
		Iterator it = def.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			resolveKey(c+1,res,def,data,key);
		}
	}
	
	
	
	private void resolveKey(int c, Resolver res, Map def, Map data, String key) throws Exception
	{
		if(c>LOOP_LIMIT) throw new Exception("Loop limit exceeded: "+c);
		
		Object value = def.get(key);
		
		if(value instanceof String)
		{
			resolveString(c,res,def,data,key,(String) value);
		}
		else if(value instanceof Map)
		{
			Map def1 = (Map) value;
			Map data1 = new HashMap();
			data.put(key,data1);
			
			resolveMap(c,res,def1,data1);
		}
		else throw new Exception("Invalid value type: "+value.getClass().getName());
	}
	
	
	
	private void resolveString(int c, Resolver res, Map def, Map data, String key, String exp) throws Exception
	{
		if(c>LOOP_LIMIT) throw new Exception("Loop limit exceeded: "+c);
		
		Set vars = (Set) getVars.t(exp);
		
		Iterator it = vars.iterator();
		while(it.hasNext())
		{
			String var = (String) it.next();
			if(data.containsKey(var)) continue;
			if(res.globalData.containsKey(var)) continue;
			
			if(def.containsKey(var))
				resolveKey(c+1,res,
				def,
				data,
				var);
			else if(res.globalDef.containsKey(var))
				resolveKey(c+1,res,
				res.globalDef,
				res.globalData,
				var);
			else throw new Exception("Undefined var: "+var);
		}
		
		Map params = new HashMap();
		params.putAll(res.globalData);
		params.putAll(data);
		
		Object v = evalWith.t(new Object[]{exp,res.opMap,params});
		data.put(key,v);
	}
	
	
	
	private class Resolver
	{
		private Map opMap;
		private Map globalDef;
		private Map globalData;
		
		public Resolver(Map opMap, Map globalDef)
		{
			this.opMap = opMap;
			this.globalDef = globalDef;
			globalData = new HashMap();
		}
	}
}
