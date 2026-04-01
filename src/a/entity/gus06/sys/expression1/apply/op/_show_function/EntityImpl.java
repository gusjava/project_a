package a.entity.gus06.sys.expression1.apply.op._show_function;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160612";}


	private Service perform;
	private Service builder;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.frame.show.function");
		builder = Outside.service(this,"gus06.sys.expression1.builder2.h");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof H) return new E1(value);
		if(value instanceof String) return new E1(builder.t(obj));
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class E1 implements E
	{
		private Object o;
		public E1(Object o) {this.o = o;}
		
		public void e() throws Exception
		{perform.p(o);}
	}
}
