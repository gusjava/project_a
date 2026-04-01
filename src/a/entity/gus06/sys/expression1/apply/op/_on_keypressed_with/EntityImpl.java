package a.entity.gus06.sys.expression1.apply.op._on_keypressed_with;

import a.framework.*;
import javax.swing.JComponent;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170819";}


	private Service perform;
	private Service findComp;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		findComp = Outside.service(this,"gus06.find.jcomponent");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof JComponent) return new T1((JComponent) obj);
		if(obj instanceof I) return new T1((JComponent) findComp.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private JComponent comp;
		public T1(JComponent comp) {this.comp = comp;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof Map) return new E1(new Object[]{comp,obj});
			return new T2(comp,obj);
		}
	}
	
	private class T2 implements T
	{
		private JComponent comp;
		private Object key;
		
		public T2(JComponent comp, Object key)
		{
			this.comp = comp;
			this.key = key;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1(new Object[]{comp,key,obj});}
	}
	
	
	private class E1 implements E
	{
		private Object data;
		public E1(Object data) {this.data = data;}
		
		public void e() throws Exception
		{perform.p(data);}
	}
}
