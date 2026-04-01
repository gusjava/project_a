package a.entity.gus06.sys.expression1.apply.op._isinstance;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160611";}


	private Service toClass;

	public EntityImpl() throws Exception
	{
		toClass = Outside.service(this,"gus06.find.class1");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		return new F1(obj);
	}
	
	
	private class F1 implements F
	{
		private Object data;
		public F1(Object data) {this.data = data;}
		
		public boolean f(Object obj) throws Exception
		{
			Class c = (Class) toClass.t(obj);
			return c.isInstance(data);
		}
	}
}
