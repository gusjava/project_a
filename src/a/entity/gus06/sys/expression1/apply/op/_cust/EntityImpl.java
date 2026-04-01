package a.entity.gus06.sys.expression1.apply.op._cust;

import a.framework.*;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;
import javax.swing.JDialog;
import javax.swing.JComponent;
import javax.swing.JCheckBox;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180221";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.cust");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof JTextComponent) return new T1(obj);
		if(obj instanceof JCheckBox) return new T1(obj);
		if(obj instanceof JComboBox) return new T1(obj);
		if(obj instanceof JLabel) return new T1(obj);
		if(obj instanceof JDialog) return new T1(obj);
		if(obj instanceof JComponent) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object value;
		public T1(Object value)
		{this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return new E1(new Object[]{value,obj});}
	}
	
	
	private class E1 implements E
	{
		private Object o;
		public E1(Object o){this.o = o;}
		
		public void e() throws Exception
		{perform.p(o);}
	}
}
