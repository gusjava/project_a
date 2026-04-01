package a.entity.gus06.sys.expression1.apply.op._framestate_i;

import a.framework.*;
import java.awt.Frame;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190305";}

	
	public EntityImpl() throws Exception
	{
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Frame) return new E1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class E1 implements E
	{
		private Object o;
		public E1(Object o) {this.o = o;}
		
		public void e() throws Exception
		{
			((Frame) o).setExtendedState(Frame.ICONIFIED);
		}
	}
}
