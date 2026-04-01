package a.entity.gus06.sys.expression1.apply.op._init;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200402";}

	
	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.data.perform.init");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Map) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return new T2(data,obj);}
	}
	
	
	private class T2 implements T
	{
		private Object data;
		private Object key;
		
		public T2(Object data, Object key)
		{
			this.data = data;
			this.key = key;
		}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Object[]{data,key,obj});}
	}
}
