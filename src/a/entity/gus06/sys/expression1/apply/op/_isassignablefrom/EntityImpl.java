package a.entity.gus06.sys.expression1.apply.op._isassignablefrom;

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
		if(obj instanceof Class) return new F1((Class) obj);
		if(obj instanceof String) return new F1((Class) toClass.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class F1 implements F
	{
		private Class data;
		public F1(Class data) {this.data = data;}
		
		public boolean f(Object obj) throws Exception
		{
			Class c = (Class) toClass.t(obj);
			return c.isAssignableFrom(data);
		}
	}
}
