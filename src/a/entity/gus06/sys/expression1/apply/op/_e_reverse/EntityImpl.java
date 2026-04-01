package a.entity.gus06.sys.expression1.apply.op._e_reverse;

import a.framework.*;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.text.JTextComponent;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151114";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.reverse");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List) return new E1(obj);
		if(obj instanceof StringBuffer) return new E1(obj);
		if(obj instanceof Object[]) return new E1(obj);
		if(obj instanceof Map) return perform.t(obj);
		
		if(obj instanceof double[]) return new E1(obj);
		if(obj instanceof int[]) return new E1(obj);
		if(obj instanceof long[]) return new E1(obj);
		if(obj instanceof float[]) return new E1(obj);
		if(obj instanceof byte[]) return new E1(obj);
		if(obj instanceof boolean[]) return new E1(obj);
		
		if(obj instanceof JPanel) return new E1(obj);
		if(obj instanceof JSplitPane) return new E1(obj);
		if(obj instanceof JTabbedPane) return new E1(obj);
		if(obj instanceof JTextComponent) return new E1(obj);
		
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
