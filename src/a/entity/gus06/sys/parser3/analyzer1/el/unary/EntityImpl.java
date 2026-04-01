package a.entity.gus06.sys.parser3.analyzer1.el.unary;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160810";}

	public static final String TYPE = "type";
	public static final String VALUE = "value";
	public static final String TYPE_SYMBOL = "symbol";



	private Service buildResult;
	
	public EntityImpl() throws Exception
	{
		buildResult = Outside.service(this,"gus06.sys.parser3.analyzer1.build.result");
	}
	
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map op = (Map) o[0];
		Map tag = (Map) o[1];
		T t = (T) o[2];
		
		try
		{
			if(!hasType(op,TYPE_SYMBOL))
				throw new Exception("Invalid first tag for unary operation: "+op);
		
			Object symbol = value(op);
		
			if(symbol.equals("!")) return result("!",t.t(tag));
			if(symbol.equals("-")) return result("-",t.t(tag));
			if(symbol.equals("&")) return result("&",t.t(tag));
			if(symbol.equals("�")) return result("�",t.t(tag));
			if(symbol.equals("@")) return result("@",t.t(tag));
			if(symbol.equals("�")) return result("�",t.t(tag));
		
			throw new Exception("Invalid symbol for unary operation: "+symbol);
		}
		catch(Exception e)
		{
			String message = "UNARY EXP: op="+op+" tag="+tag;
			throw new Exception(message,e);
		}
	}
	
	
	
	private Object value(Map m)
	{return m.get(VALUE);}
	
	private String type(Map m)
	{return (String) m.get(TYPE);}
	
	private boolean hasValue(Map m, Object value)
	{return value(m).equals(value);}
	
	private boolean hasType(Map m, String type)
	{return type(m).equals(type);}
	
	private Object result(String operator, Object content) throws Exception
	{return buildResult.t(new Object[]{operator,content});}
}
