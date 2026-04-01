package a.entity.gus06.sys.expression1.apply.op._replace1t;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161028";}


	private Service builder;
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.expression1.builder2.t");
		perform = Outside.service(this,"gus06.data.string.replacer1.t");
	}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof String) return new T1((String) value, opMap);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private String line;
		private Map opMap;
		
		public T1(String line, Map opMap)
		{
			this.line = line;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{return new T2(line,toString_(obj),opMap);}
	}
	
	
	private class T2 implements T
	{
		private String line;
		private String s1;
		private Map opMap;
		
		public T2(String line, String s1, Map opMap)
		{
			this.line = line;
			this.s1 = s1;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{return (String) perform.t(new Object[]{line,s1,toT(obj)});}
		
		private T toT(Object obj) throws Exception
		{return (T) builder.t(new Object[]{obj,opMap});}
	}
	
	
	
	
	private String toString_(Object obj) throws Exception
	{
		if(obj instanceof Boolean) return ""+obj;
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof String) return ""+obj;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
