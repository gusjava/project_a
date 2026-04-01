package a.entity.gus06.sys.expression1.apply.op._splitfile;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250913";}
	


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.splitfile");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return new T1(obj);
		if(obj instanceof Map) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return new E1(data,obj);}
	}
	
	
	private class E1 implements E
	{
		private Object data1;
		private Object data2;
		
		public E1(Object data1, Object data2)
		{
			this.data1 = data1;
			this.data2 = data2;
		}
		
		public void e() throws Exception
		{perform.p(new Object[]{data1,data2});}
	}
}
