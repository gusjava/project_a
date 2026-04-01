package a.entity.gus06.sys.expression1.builder2.f;

import a.framework.*;
import java.util.Map;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160120";}


	private Service tToF;
	private Service mapToF;
	private Service colToF;
	private Service numberToF;
	private Service booleanToF;
	
	private Service builder1;
	private Service simpleFilter;


	public EntityImpl() throws Exception
	{
		tToF = Outside.service(this,"gus06.convert.ttof");
		mapToF = Outside.service(this,"gus06.convert.maptof");
		colToF = Outside.service(this,"gus06.collection.contains.coltof");
		numberToF = Outside.service(this,"gus06.convert.numbertof");
		booleanToF = Outside.service(this,"gus06.convert.booleantof");
		
		builder1 = Outside.service(this,"gus06.sys.expression1.builder1.f");
		simpleFilter = Outside.service(this,"gus06.filter.object.fromrule");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Map opMap = (Map) o[1];
		
		return findF(data,opMap);
	}
	
	private F findF(Object data, Map opMap) throws Exception
	{
		if(data instanceof F)		return (F) data;
		if(data instanceof T) 		return (F) tToF.t(data);
		if(data instanceof Map) 	return (F) mapToF.t(data);
		if(data instanceof Collection)	return (F) colToF.t(data);
		if(data instanceof Number)	return (F) numberToF.t(data);
		if(data instanceof Boolean)	return (F) booleanToF.t(data);
		if(data instanceof String)	return stringToF((String) data,opMap);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	private F stringToF(String data, Map opMap) throws Exception
	{
		if(opMap.containsKey("_"+data))
			return opToF(data,opMap);
		
		F f = (F) simpleFilter.r(data);
		if(f!=null) return f;
		
		return (F) builder1.t(new Object[]{data,opMap});
	}
	
	
	
	private F opToF(String data, Map opMap) throws Exception
	{
		T op = (T) opMap.get("_"+data);
		return new F0(op,opMap);
	}
	
	
	private class F0 implements F
	{
		private T t;
		private Map opMap;
		
		public F0(T t, Map opMap)
		{this.t = t;this.opMap = opMap;}
		
		public boolean f(Object obj) throws Exception
		{
			Boolean b = (Boolean) t.t(new Object[]{obj,opMap});
			return b.booleanValue();
		}
	}
}
