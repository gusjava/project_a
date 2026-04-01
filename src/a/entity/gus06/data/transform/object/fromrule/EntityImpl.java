package a.entity.gus06.data.transform.object.fromrule;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Date;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20160818";}
	
	
	private Service ruleToIndex;
	
	private Map map;


	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
		
		map = new HashMap();
		
		put("first",Outside.service(this,"gus06.data.perform.first"));
		put("height",Outside.service(this,"gus06.data.perform.height"));
		put("id",Outside.service(this,"gus06.feature.build.t.id"));
		put("last",Outside.service(this,"gus06.data.perform.last"));
		put("lower",Outside.service(this,"gus06.data.perform.lower"));
		put("max",Outside.service(this,"gus06.data.perform.max"));
		put("min",Outside.service(this,"gus06.data.perform.min"));
		put("random",Outside.service(this,"gus06.data.perform.random"));
		put("size",Outside.service(this,"gus06.data.perform.size"));
		put("sort",Outside.service(this,"gus06.data.perform.sort"));
		put("sum",Outside.service(this,"gus06.data.perform.sum"));
		put("upper",Outside.service(this,"gus06.data.perform.upper"));
		put("width",Outside.service(this,"gus06.data.perform.width"));
		put("stddev",Outside.service(this,"gus06.data.perform.stddeviation"));
		put("titled",Outside.service(this,"gus06.data.perform.titled"));
		put("variance",Outside.service(this,"gus06.data.perform.variance"));
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(map.containsKey(key))	return map.get(key);
		if(key.startsWith("f_"))	return new T_Field(key.substring(2));
		if(key.matches("m[0-9]+"))	return new T_m1(key);
		if(isInt(key))			return new T_int(key);
		
		return null;
	}
	
	
	
	private void put(String key, T t)
	{map.put(key,new T_Wrap(t));}
	
	
	
	private boolean isInt(String s)
	{
		try{Integer.parseInt(s);return true;}
		catch(NumberFormatException e){return false;}
	}
	
	
	
	private class T_int implements T
	{
		private String data;
		public T_int(String data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof List)
			{
				Integer index = (Integer) ruleToIndex.t(new Object[]{obj,data});
				return index==null ? null : ((List) obj).get(index.intValue());
			}
			if(obj instanceof Object[])
			{
				Integer index = (Integer) ruleToIndex.t(new Object[]{obj,data});
				return index==null ? null : ((Object[]) obj)[index.intValue()];
			}
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	
	private class T_m1 implements T
	{
		private String data;
		public T_m1(String data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof List)
			{
				Integer index = (Integer) ruleToIndex.t(new Object[]{obj,data});
				return index==null ? null : ((List) obj).get(index.intValue());
			}
			if(obj instanceof Object[])
			{
				Integer index = (Integer) ruleToIndex.t(new Object[]{obj,data});
				return index==null ? null : ((Object[]) obj)[index.intValue()];
			}
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	
	private class T_Field implements T
	{
		private String data;
		public T_Field(String data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof Map)
			{
				Map m = (Map) obj;
				if(!m.containsKey(data)) return null;
				return m.get(data);
			}
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	
	private class T_Wrap implements T
	{
		private T t;
		public T_Wrap(T t) {this.t = t;}
		
		public Object t(Object obj) throws Exception
		{return t.t(obj);}
	}
}
