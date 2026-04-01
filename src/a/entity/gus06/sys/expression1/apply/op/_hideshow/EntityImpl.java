package a.entity.gus06.sys.expression1.apply.op._hideshow;

import a.framework.*;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160915";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.hideshow");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof I) return new E1(obj);
		if(obj instanceof JComponent) return new E1(obj);
		if(obj instanceof JFrame) return new E1(obj);
		if(obj instanceof JDialog) return new E1(obj);
		
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
