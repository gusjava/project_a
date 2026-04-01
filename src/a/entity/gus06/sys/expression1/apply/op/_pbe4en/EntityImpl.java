package a.entity.gus06.sys.expression1.apply.op._pbe4en;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170520";}


	private Service builder;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.crypto.pbe4.object.encrypt");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return new T1(obj);
		if(obj instanceof byte[]) return new T1(obj);
		if(obj instanceof Map) return new T1(obj);
		if(obj instanceof List) return new T1(obj);
		if(obj instanceof Set) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data){this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			String code = (String) obj;
			T t = (T) builder.t(code);
			return t.t(data);
		}
	}
}
