package a.entity.gus06.sys.expression1.apply.op._e_insert;

import a.framework.*;
import java.util.List;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160131";}


	private Service perform;
	
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.insert");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		
		if(value instanceof List) return new T1(value);
		if(value instanceof StringBuffer) return new T1(value);
		if(value instanceof StringBuilder) return new T1(value);
		if(value instanceof JTextComponent) return new T1(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object value;
		public T1(Object value)
		{this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return new T2(value,obj);}
	}
	
	private class T2 implements T
	{
		private Object value;
		private Object index;
		
		public T2(Object value, Object index)
		{
			this.value = value;
			this.index = index;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1(new Object[]{value,index,obj});}
	}
	
	
	private class E1 implements E
	{
		private Object o;
		public E1(Object o){this.o = o;}
		
		public void e() throws Exception
		{perform.p(o);}
	}
}