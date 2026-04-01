package a.entity.gus06.sys.expression1.apply.op._setdisplay;

import a.framework.*;
import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170806";}

	
	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.swing.comp.cust2.display");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof JLabel) return new T1(obj);
		if(obj instanceof JFrame) return new T1(obj);
		if(obj instanceof AbstractButton) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private Object value;
		public T1(Object value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return new E1((String) obj,value);}
	}
	
	
	private class E1 implements E
	{
		private String display;
		private Object data;
		
		public E1(String display, Object data)
		{
			this.display = display;
			this.data = data;
		}
		
		public void e() throws Exception
		{perform.v(display,data);}
	}
}
