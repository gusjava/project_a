package a.entity.gus06.sys.expression1.apply.op._e_swap;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170423";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.swap");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof List) return new T1(obj);
		if(obj instanceof StringBuffer) return new T1(obj);
		if(obj instanceof Object[]) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object value;
		public T1(Object value){this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof int[])
			{
				int[] k = (int[]) obj;
				if(k.length!=2) throw new Exception("Invalid array length: "+k.length);
				
				Integer n1 = Integer.valueOf(k[0]);
				Integer n2 = Integer.valueOf(k[1]);
				return new E1(new Object[]{value,n1,n2});
			}
			if(obj instanceof Object[])
			{
				int[] k = (int[]) obj;
				if(k.length!=2) throw new Exception("Invalid array length: "+k.length);
				
				Object n1 = k[0];
				Object n2 = k[1];
				return new E1(new Object[]{value,n1,n2});
			}
			if(obj instanceof List)
			{
				List l = (List) obj;
				if(l.size()!=2) throw new Exception("Invalid list length: "+l.size());
				
				Object n1 = l.get(0);
				Object n2 = l.get(1);
				return new E1(new Object[]{value,n1,n2});
			}
			if(obj instanceof Integer)
			{
				return new T2(value,obj);
			}
			if(obj instanceof String)
			{
				return new T2(value,obj);
			}
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	private class T2 implements T
	{
		private Object value;
		private Object n1;
		
		public T2(Object value, Object n1)
		{
			this.value = value;
			this.n1 = n1;
		}
		
		public Object t(Object obj) throws Exception
		{
			return new E1(new Object[]{value,n1,obj});
		}
	}
	
	
	
	private class E1 implements E
	{
		private Object o;
		public E1(Object o){this.o = o;}
		
		public void e() throws Exception
		{perform.p(o);}
	}
}
