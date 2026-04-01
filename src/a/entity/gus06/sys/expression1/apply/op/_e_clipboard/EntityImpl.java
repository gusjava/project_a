package a.entity.gus06.sys.expression1.apply.op._e_clipboard;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.Collection;
import java.awt.Image;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151115";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.clipboard.access");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof File) return new E1(obj);
		if(obj instanceof File[]) return new E1(obj);
		if(obj instanceof List) return new E1(obj);
		if(obj instanceof String) return new E1(obj);
		if(obj instanceof Image) return new E1(obj);
		
		if(obj instanceof StringBuffer) return new E1(((StringBuffer)obj).toString());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class E1 implements E
	{
		private Object o;
		public E1(Object o) {this.o = o;}
		
		public void e() throws Exception
		{perform.p(o);}
	}
}
