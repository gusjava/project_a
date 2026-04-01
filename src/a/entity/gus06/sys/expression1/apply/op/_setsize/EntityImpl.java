package a.entity.gus06.sys.expression1.apply.op._setsize;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220611";}

	
	private Service findDim;
	
	public EntityImpl() throws Exception
	{findDim = Outside.service(this,"gus06.find.dimension");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof JComponent) return new T1((JComponent) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private JComponent comp;
		public T1(JComponent comp) {this.comp = comp;}
		
		public Object t(Object obj) throws Exception
		{return new E1(obj,comp);}
	}
	
	
	private class E1 implements E
	{
		private Object value;
		private JComponent comp;
		
		public E1(Object value, JComponent comp)
		{
			this.value = value;
			this.comp = comp;
		}
		
		public void e() throws Exception
		{
			Dimension dim = (Dimension) findDim.t(value);
			comp.setSize(dim);
		}
	}
}