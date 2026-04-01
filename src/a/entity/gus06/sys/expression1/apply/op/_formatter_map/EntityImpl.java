package a.entity.gus06.sys.expression1.apply.op._formatter_map;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160820";}


	private Service formatter;
	
	public EntityImpl() throws Exception
	{
		formatter = Outside.service(this,"gus06.map.string.formatter1");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value instanceof String) return new T1(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object value;
		
		public T1(Object value)
		{this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			Map map = (Map) obj;
			return formatter.t(new Object[]{map,value});
		}
	}
}
