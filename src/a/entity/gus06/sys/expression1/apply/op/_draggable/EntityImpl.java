package a.entity.gus06.sys.expression1.apply.op._draggable;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.Component;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190519";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.comp.cust.dragframe");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof I) return new E1(((I) obj).i());
		if(obj instanceof Component) return new E1(obj);
		
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
