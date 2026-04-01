package a.entity.gus06.sys.expression1.apply.op._has_deep;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220210";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.map.deep.get");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Object[]) return new F1(obj);
		if(obj instanceof List) return new F1(obj);
		if(obj instanceof Map) return new F1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class F1 implements F
	{
		private Object value;
		public F1(Object value) {this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{return perform.f(new Object[]{value,obj});}
	}
}