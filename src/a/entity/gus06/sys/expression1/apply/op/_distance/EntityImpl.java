package a.entity.gus06.sys.expression1.apply.op._distance;

import a.framework.*;
import java.util.List;
import java.util.Date;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}


	private Service perform;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.distance");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return new T1(obj);
		if(obj instanceof Number) return new T1(obj);
		if(obj instanceof Date) return new T1(obj);
		if(obj instanceof Color) return new T1(obj);
		if(obj instanceof double[]) return new T1(obj);
		if(obj instanceof int[]) return new T1(obj);
		if(obj instanceof List) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Object[]{data,obj});}
	}
	
}
