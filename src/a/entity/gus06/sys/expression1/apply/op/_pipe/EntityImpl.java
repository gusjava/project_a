package a.entity.gus06.sys.expression1.apply.op._pipe;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151118";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.pipe");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof T[]) return perform.t(obj);
		if(obj instanceof H[]) return perform.t(obj);
		if(obj instanceof Map[]) return perform.t(obj);
		if(obj instanceof Object[]) return perform.t(obj);
		if(obj instanceof List) return perform.t(obj);
		
		if(obj instanceof G) return new T1(obj);
		if(obj instanceof I) return new T1(obj);
		if(obj instanceof T) return new T1(obj);
		if(obj instanceof F) return new T1(obj);
		if(obj instanceof H) return new T1(obj);
		if(obj instanceof Map) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object value;
		public T1(Object value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Object[]{value,obj});}
	}
}
