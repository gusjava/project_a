package a.entity.gus06.sys.expression1.apply.op._setdivider;

import a.framework.*;
import javax.swing.JSplitPane;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191204";}

	
	private Service changeDivider;
	
	public EntityImpl() throws Exception
	{changeDivider = Outside.service(this,"gus06.swing.splitpane.cust3.divider");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof JSplitPane) return new T1((JSplitPane) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private JSplitPane split;
		public T1(JSplitPane split) {this.split = split;}
		
		public Object t(Object obj) throws Exception
		{return new E1(obj,split);}
	}
	
	
	private class E1 implements E
	{
		private Object value;
		private JSplitPane split;
		
		public E1(Object value, JSplitPane split)
		{
			this.value = value;
			this.split = split;
		}
		
		public void e() throws Exception
		{changeDivider.p(new Object[]{split,value});}
	}
}
