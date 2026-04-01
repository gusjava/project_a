package a.entity.gus06.sys.expression1.apply.op._e_keepall_nempty;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231107";}


	private Service perform;
	private Service filter;
	
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.keepall");
		filter = Outside.service(this,"gus06.data.filter.isnempty");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List) return new E1(obj);
		if(obj instanceof Set) return new E1(obj);
		if(obj instanceof Map) return new E1(obj);
		if(obj instanceof File) return new E1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class E1 implements E
	{
		private Object obj;
		public E1(Object obj){this.obj = obj;}
		
		public void e() throws Exception
		{perform.p(new Object[]{obj,filter});}
	}
}