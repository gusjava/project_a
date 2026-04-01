package a.entity.gus06.sys.expression1.apply.op._parsejava_methoddata_forsign;

import a.framework.*;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231023";}

	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.javaparser1.extract.method.data.forsign");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return new T1(obj);
		if(obj instanceof File) return new T1(obj);
		if(obj instanceof InputStream) return new T1(obj);
		if(obj instanceof Reader) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data)
		{this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			String sign = (String) obj;
			return perform.t(new Object[]{data,sign});
		}
	}
}