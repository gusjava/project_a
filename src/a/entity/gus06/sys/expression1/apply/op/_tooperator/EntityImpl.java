package a.entity.gus06.sys.expression1.apply.op._tooperator;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151205";}

	private Service builder;
	
	public EntityImpl() throws Exception
	{builder = Outside.service(this,"gus06.sys.expression1.builder1.t");}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof T)
		return new Operator((T) value);
		
		if(value instanceof String)
		return new Operator(stringToT((String) value,opMap));
			
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	private T stringToT(String s, Map opMap) throws Exception
	{return (T) builder.t(new Object[]{s,opMap});}
	
	
	
	
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
