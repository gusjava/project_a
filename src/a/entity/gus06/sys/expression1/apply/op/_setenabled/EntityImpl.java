package a.entity.gus06.sys.expression1.apply.op._setenabled;

import a.framework.*;
import java.awt.Component;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250517";}

	
	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.swing.comp.setenabled");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Component) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private Object value;
		public T1(Object value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return new E1(new Object[]{value,obj});}
	}
	
	
	private class E1 implements E
	{
		private Object[] data;
		public E1(Object[] data) {this.data = data;}
		
		public void e() throws Exception
		{perform.p(data);}
	}
}
