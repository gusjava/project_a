package a.entity.gus06.sys.expression1.apply.op._checkall;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}


	private Service builder;
	private Service filter;
	
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.expression1.builder2.f");
		filter = Outside.service(this,"gus06.data.filter.check.all");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof List) return new F1(value,opMap);
		if(value instanceof Set) return new F1(value,opMap);
		if(value instanceof Map) return new F1(value,opMap);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class F1 implements F
	{
		private Object value;
		private Map opMap;
		
		public F1(Object value, Map opMap)
		{
			this.value = value;
			this.opMap = opMap;
		}
		
		public boolean f(Object obj) throws Exception
		{return filter.f(new Object[]{value,toF(obj)});}
		
		private F toF(Object obj) throws Exception
		{return (F) builder.t(new Object[]{obj,opMap});}
	}
}
