package a.entity.gus06.sys.expression1.apply.op._e_close;

import a.framework.*;
import java.io.Closeable;
import java.awt.Window;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151115";}


	private Service perform;
	
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.close");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof AutoCloseable) return new E1(obj);
		if(obj instanceof Closeable) return new E1(obj);
		if(obj instanceof Window) return new E1(obj);
		
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
