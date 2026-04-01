package a.entity.gus06.filter.object.fromrule;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20160808";}
	
	

	private Map map;
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("null",Outside.service(this,"gus06.data.filter.isnull"));
		put("empty",Outside.service(this,"gus06.data.filter.isempty"));
		put("id",Outside.service(this,"gus06.feature.build.f.id"));
		put("int",Outside.service(this,"gus06.data.filter.istype.int1"));
		put("intp",Outside.service(this,"gus06.data.filter.istype.intp"));
		put("intpp",Outside.service(this,"gus06.data.filter.istype.intpp"));
		put("double",Outside.service(this,"gus06.data.filter.istype.double1"));
		put("boolean",Outside.service(this,"gus06.data.filter.istype.boolean1"));
		put("true",Outside.service(this,"gus06.data.filter.istrue"));
		put("false",Outside.service(this,"gus06.data.filter.isfalse"));
		put("String",Outside.service(this,"gus06.data.filter.istype.string"));
		put("Color",Outside.service(this,"gus06.data.filter.istype.color"));
		put("Date",Outside.service(this,"gus06.data.filter.istype.date"));
		put("File",Outside.service(this,"gus06.data.filter.istype.file"));
		put("List",Outside.service(this,"gus06.data.filter.istype.list"));
		put("Set",Outside.service(this,"gus06.data.filter.istype.set"));
		put("Map",Outside.service(this,"gus06.data.filter.istype.map"));
		
		put("even",Outside.service(this,"gus06.data.filter.number.even"));
		put("odd",Outside.service(this,"gus06.data.filter.number.odd"));
		put("prime",Outside.service(this,"gus06.data.filter.number.prime"));
		
		put("str","String");
		put("str10",new F_String_limit(10));
		put("str20",new F_String_limit(20));
		put("str50",new F_String_limit(50));
		put("str100",new F_String_limit(100));
		put("str200",new F_String_limit(200));
		put("str500",new F_String_limit(500));
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(map.containsKey(key)) return map.get(key);
		return null;
	}
	
	
	
	private void put(String key, F f)
	{map.put(key,new F_Wrap(f));}
	
	private void put(String key, String key1)
	{map.put(key,map.get(key1));}
	
	
	private class F_Wrap implements F
	{
		private F f;
		public F_Wrap(F f) {this.f = f;}
		
		public boolean f(Object obj) throws Exception
		{return f.f(obj);}
	}
	
	
	
	
	private class F_String_limit implements F
	{
		private int limit;
		public F_String_limit(int limit)
		{this.limit = limit;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			if(!(obj instanceof String)) return false;
			
			String s = (String) obj;
			return s.length()<=limit;
		}
	}
}
