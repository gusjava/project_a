package a.entity.gus06.sys.expression1.apply.op._transfer;

import a.framework.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180319";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.transfer");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof InputStream) return new T1(obj);
		if(obj instanceof OutputStream) return new T1(obj);
		if(obj instanceof Object[]) return new E1(obj);
		if(obj instanceof List) return new E1(obj);
		if(obj instanceof Map) return new E1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return new E1(new Object[]{data,obj});}
	}
	
	private class E1 implements E
	{
		private Object data;
		public E1(Object data) {this.data = data;}
		
		public void e() throws Exception
		{perform.p(data);}
	}
}
